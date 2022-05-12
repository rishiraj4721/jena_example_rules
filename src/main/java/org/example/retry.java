package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class retry {
    static final String inputFileName = "nutritionontology.owl";
    public static void main(String[] args) throws FileNotFoundException{
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/chil";
        String user = "postgres";
        String password = "hellothere";
        try {
            // Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");


        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM, null);
        InputStream in = new FileInputStream(inputFileName);
		model.read(in, "");
		String med = "http://www.childhealthimprints.com/SampleOntology/Testing/";

        org.apache.jena.rdf.model.Resource r = model.getResource(med);
        Property hasDayOfLife = model.getProperty(med, "hasDayOfLife");
		Property hasFeedIntolerance = model.getProperty(med, "hasFeedIntolerance");
		Property hasGestationalAgeAtBirth = model.getProperty(med, "hasGestationalAgeAtBirth");
		Property hasFeedingVolume = model.getProperty(med, "hasFeedingVolume");
		Property hasFeedingAdvancement = model.getProperty(med, "hasFeedingAdvancement");
		Property hasRiskFactor = model.getProperty(med, "hasRiskFactor");
		Property givenFeedVolume = model.getProperty(med, "givenFeedVolume");
		Property hasBabyID = model.getProperty(med, "hasBabyID");
		Property hasMonoTwinStatus = model.getProperty(med, "hasMonoTwinStatus"); //
		Property hasCordGaspHValue = model.getProperty(med, "hasCordGaspHValue"); //
		Property hasBaseDeficitValue = model.getProperty(med, "hasBaseDeficitValue"); //
		Property hasChestCompressionDuration = model.getProperty(med, "hasChestCompressionDuration"); //
		Property pressorGiven = model.getProperty(med, "pressorGiven"); //
		Property hasVomitColor = model.getProperty(med, "hasVomitColor"); //
		Property hasVomitVolume = model.getProperty(med, "hasVomit"); //

		Property hasAbdominalDistension = model.getProperty(med, "hasAbdominalDistension");
		Property hasSkinDiscoloration = model.getProperty(med, "hasSkinDiscoloration");
		Property hasAbdominalTenderness = model.getProperty(med, "hasAbdominalTenderness");
		Property hasVisibleBowelLoop = model.getProperty(med, "hasVisibleBowelLoop");
		Property bloodPresentInStool = model.getProperty(med, "bloodPresentInStool");
		Property hasMetabolicAcidosis = model.getProperty(med, "hasMetabolicAcidosis");
		Property hasApnea = model.getProperty(med, "hasApnea");
		Property hasRespiratoryDistress = model.getProperty(med, "hasRespiratoryDistress");
		Property hasLethargy = model.getProperty(med, "hasLethargy");
		Property hasTemperatureInStability = model.getProperty(med, "hasTemperatureInStability");
		Property haslowPlateletCount = model.getProperty(med, "haslowPlateletCount");
		Property hasGastricAspirate = model.getProperty(med, "hasGastricAspirate");

		Property hasXRayAbdominStatus = model.getProperty(med, "hasX-RayAbdominStatus");
		Property hasGastricAspirateAbnormalColor = model.getProperty(med, "hasGastricAspirateAbnormalColor");
		Property hasLongCapillaryRefillTime = model.getProperty(med, "hasLongCapillaryRefillTime");
		Property hasCentralPeripheralDifference = model.getProperty(med, "hasCentralPeripheralDifference");
		Property hasTechycardia = model.getProperty(med, "hasTechycardia");
		Property hasBloodPressure = model.getProperty(med, "hasBloodPressure");

		Property hasLowBloodPressure = model.getProperty(med, "hasLowBloodPressure");
		Property hasColdPeripheries = model.getProperty(med, "hasColdPeripheries");
		Property hasLactateLevel = model.getProperty(med, "hasLactateLevel");
		Property hasNecrotizingEnterocolitis = model.getProperty(med, "hasNecrotizingEnterocolitis");
		Property hasUniqueID = model.getProperty(med, "hasUniqueID");


        try{
            String getUHID = "select uhid from apollo.baby_detail";
            PreparedStatement ps = con.prepareStatement(getUHID);
        }
    }
}
