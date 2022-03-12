package org.example;

import java.util.Iterator;
import java.util.List;

import org.apache.jena.atlas.csv.CSVParser;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;

public class app3{
    public static void main(String[] args){
        // Model schema = RDFDataMgr.loadModel("nutritionontology.owl");
        // Model data = RDFDataMgr.loadModel("");
    
        // List<Rule> rules = Rule.rulesFromURL("smallruleset.rules");
        // Reasoner reasoner = new GenericRuleReasoner(rules);

        // reasoner = reasoner.bindSchema(schema);
        // InfModel infmodel = ModelFactory.createInfModel(reasoner, data);

        // Iterator<Statement> it = infmodel.listStatements();

        // listobjects(infmodel);
        read_csv("nutritional_compliance.csv");
    }
    static void read_csv(String filename){
        CSVParser csvparse = CSVParser.create(filename);

        List<String> s = csvparse.parse1();
        System.out.println(s.toString());
        int i=0;
        while (i<10){
            s = csvparse.parse1();
            System.out.println(s.toString());
            i++;
        }
    }
    static void resourceinf(InfModel infmodel,String rName){
        Resource nForce = infmodel.getResource("urn:x-hp:eg/"+rName);
        System.out.println(rName+" :");
        printStatements(infmodel,nForce,null,null);
    }
    static void listobjects(InfModel model){
        NodeIterator ni = model.listObjects();
        while(ni.hasNext()){
            System.out.println(ni.next());
        }
    }
    public static void printStatements(Model m, Resource s, Property p, Resource o) { 
        for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) { 
            Statement stmt = i.nextStatement(); 
            System.out.println(" - " + PrintUtil.print(stmt)); 
        } 
    } 
}