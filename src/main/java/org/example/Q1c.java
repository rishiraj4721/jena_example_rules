package org.example;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.riot.RDFDataMgr;

public class Q1c{
    public static void main(String[] args){
        Model model = RDFDataMgr.loadModel("Q1a.ttl");
        Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
        InfModel inf = ModelFactory.createInfModel(reasoner, model);

        listobjects(inf);
    }
    static void listobjects(InfModel model){
        NodeIterator ni = model.listObjects();
        while(ni.hasNext()){
            System.out.println(ni.next());
        }
    }
}