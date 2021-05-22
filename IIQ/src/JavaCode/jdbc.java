package JavaCode;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import sailpoint.object.Identity;
import sailpoint.object.ProvisioningPlan;
import sailpoint.tools.GeneralException;

public class jdbc {
	
	public static void JDBCLDAPPROVISION() throws GeneralException{
		
		sailpoint.api.SailPointContext context =null;
		
		Logger log =Logger.getLogger("sailpoint.services.bshdemo");
		
		 	log.setLevel(Level.DEBUG);
		 	
		 	if(log.isDebugEnabled())
		 		log.debug("Provisioning Plan Starting");
		 	
		 	Identity identityName = (Identity) context.getObjectByName(Identity.class,"Adam.Kennedy");
		 	
		 	 ProvisioningPlan plan = new ProvisioningPlan();
		 	
	}

	
	}
