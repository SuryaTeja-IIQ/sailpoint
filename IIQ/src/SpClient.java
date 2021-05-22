import sailpoint.api.SailPointContext;
import sailpoint.api.SailPointFactory;
import sailpoint.object.Identity;
import sailpoint.spring.SpringStarter;
import sailpoint.tools.GeneralException;

public class SpClient {
      
       /**
        * Create Sailpoint context
        * @return
        */

       private SailPointContext getSailpointContext() {
              SailPointContext context =null;
              String override=null;
              SpringStarter ss= new SpringStarter ("iiqBeans.xml",override);
              String configFile=ss.getConfigFile();
              System.out.println("config File::"+configFile);
              String[] services = {"Task","Request"};
              SpringStarter.setWhitelistedServices(services);
              SpringStarter.suppressSchedulers();
              SpringStarter.setSuppressVersionChecker(true);
              ss.start();
              System.out.println("HERE");

              try {
                     context= SailPointFactory.createContext("identityiq");
                     if(context !=null) {
                           System.out.println("Got Connection "+context);
                           context.authenticate("spadmin", "admin"); //Provide current id password.
                     }else {
                           System.out.println("null Connection ");
                     }
              }catch(Exception e) {
                     e.printStackTrace();
              }
              return context;
       }
       /**
        * Search Identity
        * @param context
        * @param identiyName
        * @return
        */

//       private Identity searchIdentity (SailPointContext context, String identiyName) {
//              Identity identity =null;
//              Boolean inactivityflag=true;
//             // System.out.println("Nirupam");
//
//              try {
//                     identity= context.getObject(Identity.class,identiyName);
//                     System.out.println("First Name::"+identity.getFirstname());
//                     System.out.println("Last Name::"+identity.getLastname());
//                     inactivityflag=(Boolean) identity.getAttribute("inactive");
//                     System.out.println("inactivityflag1111::"+inactivityflag);
//              } catch (GeneralException e) {
//                     // TODO Auto-generated catch block
//                     e.printStackTrace();
//              }
//              System.out.println("return identity");
//              return identity;
//       }

       public static void main (String[] args) {
          String path= "C:\\Users\\Asus\\Documents\\sailpoint\\apache-tomcat-9.0.41\\webapps\\identityiq"; // provide the path from your install Directory
              System.setProperty("SPHOME",path);
              System.setProperty("SP_HOME",path); 
              System.setProperty("sailpoint.home",path);
              SailPointContext context =null;
              System.out.println("Establishing Connection ");
              SpClient sc=new SpClient();
              context =sc.getSailpointContext();
             // sc.searchIdentity(context,"spadmin");
              Identity identity = new Identity();
              identity.setName("rita.sharma");
              identity.setFirstname("rita");
              identity.setLastname("sharma");
              identity.setEmail("rita.lr.sharma@gmail.com");
              Identity manager = null;
			try {
				manager = context.getObject(Identity.class,"spadmin");
			} catch (GeneralException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
              System.out.println("got identity");
              identity.setManager(manager);
              
              
              try {
            	  System.out.println("identity creaton started");
            	 context.saveObject(identity);
            	 System.out.println("identity saved successfully");
                     context.close();
              } catch (GeneralException e) {
                     e.printStackTrace();
              }
       }

}