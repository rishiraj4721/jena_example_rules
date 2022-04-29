package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.reasoner.rulesys.FBRuleReasoner;

public class pgconnect2 {
    static final String inputFileName = "NutritionOntology15Jan.owl";
	static Property hasGestationalAgeAtBirth;
	static Property hasFeedIntolerance;
	static Property hasDayOfLife;

    public static void main(String args[]) throws SQLException, FileNotFoundException {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/chil";
        String user = "postgres";
        String password = "hellothere";
        try {
            //  Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        // PreparedStatement pst = con.prepareStatement("SELECT * FROM apollo.nutritional_compliance ORDER BY nutritionalcompianceid ASC LIMIT 10");
        // ResultSet rs = pst.executeQuery();

        // while (rs.next()) {
        //     System.out.print(rs.getInt(1));
        //     System.out.print(": ");
        //     System.out.println(rs.getString(2));
        // }
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM, null);
        InputStream in = new FileInputStream(inputFileName);
        model.read(in, "");
        String med = "http://www.childhealthimprints.com/NutritionalGuidelines/";
        org.apache.jena.rdf.model.Resource r = model.getResource(med);
        
        hasGestationalAgeAtBirth = model.getProperty(med, "hasGestationalAgeAtBirth");
        // System.out.println(hasGestationalAgeAtBirth.toString());

        List<Rule> rules = Rule.rulesFromURL("smallruleset.rules");
        GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance()
                .create(null);
        ((FBRuleReasoner) reasoner).setRules(rules);
        ((GenericRuleReasoner) reasoner).setMode(GenericRuleReasoner.FORWARD_RETE);
        reasoner.setDerivationLogging(true);

        InfModel inf = ModelFactory.createInfModel(reasoner, model);
        // System.out.println(inf.toString());
        listobjects(inf);
    }
    static void listobjects(InfModel model){
        StmtIterator ni = model.listStatements();
        while(ni.hasNext()){
            System.out.println(ni.next());
        }
    }
}