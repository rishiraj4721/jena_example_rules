package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.jena.base.Sys;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.FBRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.util.iterator.ExtendedIterator;
/**
 * Hello world!
 *
 */
public class Appn 
{
    static String ontologyFilePath = "second.owl";
    
    // static Property admits;
    // static Property employs;
    // static Property offers;
    // static Property study_at;
    // static Property TA_for;
    // static Property take;
    // static Property taken_by;
    // static Property taught_by;
    // static Property teaches;
    
    // static Property CourseID;
    // static Property email;
    // static Property name;
    // static Property number_of_courses;
    // static Property number_of_registered_students;
    // static Property number_of_students;
    // static Property number_oof_teachers;
    // static Property personal_information;
    // static Property rollno;


    public static void main( String[] args ) throws FileNotFoundException
    {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM, null);
        // use the FileManager to find the input file
        InputStream in = new FileInputStream(ontologyFilePath);
        model.read(in, "");

        String med = "http://www.semanticweb.org/drbn/ontologies/2022/0/untitled-ontology-6";
        Resource r = model.getResource(med);

        // admits = model.getProperty(med, "admits");
        // System.out.println(admits);
        // employs = model.getProperty(med, "employs");

        // ExtendedIterator<ObjectProperty> objectProperties = model.listObjectProperties();
        // while(objectProperties.hasNext()){
        //     System.out.println(objectProperties.next().getLocalName());
        // }
        // System.out.println("");
        // ExtendedIterator<DatatypeProperty> dataProperties = model.listDatatypeProperties();
        // while(dataProperties.hasNext()){
        //     System.out.println(dataProperties.next());
        // }
        List<Rule> rules = Rule.rulesFromURL("example1.rules");
        // String rules = "[rule1: (?a eg:p ?b) (?b eg:p ?c) -&gt; (?a eg:p ?c)]";
		GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance().create(null);
        // Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
		((FBRuleReasoner) reasoner).setRules(rules);
		((GenericRuleReasoner) reasoner).setMode(GenericRuleReasoner.FORWARD_RETE);
		reasoner.setDerivationLogging(true);
					
	    InfModel inf = ModelFactory.createInfModel(reasoner, model);
        StmtIterator si = inf.getDeductionsModel().listStatements();
        while(si.hasNext()){
            System.out.println((si.next()));
        }
        // Resource nForce = inf.getResource("urn:x-hp:eg/nForce");
        // System.out.println("nForce :");
        // printStatements(inf,nForce,null,null);

		// org.apache.jena.rdf.model.RDFWriter writer = model.getWriter("TTL");
		// File file= new File("infmodel1");
		// writer.write(inf, new FileOutputStream(file), med);
        // System.out.println(inf);
        // NodeIterator ni = inf.listObjects();
        // while(ni.hasNext()){
        //     System.out.println(ni.next());
        // }
        // inf.write(System.out,"TURTLE");
        System.out.println( "Hello World!" );
    }
    public static void printStatements(Model m, Resource s, Property p, Resource o) { 
        for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) { 
            Statement stmt = i.nextStatement(); 
            System.out.println(" - " + PrintUtil.print(stmt)); 
        } 
    } 
}
