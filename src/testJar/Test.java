package testJar;

import java.util.Arrays;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		String[] s1 = {  "B","A", "C" };
		String[] s2 = { "A", "B", "C" };
		Arrays.sort(s1);
		Arrays.sort(s2);
		System.out.println("compareTwoStrings : "+compareTwoStrings(s1,s2));
		// TODO Auto-generated method stub
		//	String str="{\"VxmlService\":{\"FailOverScriptUrl\":\"\",\"LoadSharingPolicy\":\"Priority\",\"name\":\"v1\",\"desc\":\"\",\"Enabled\":\"true\",\"parent\":\"default\",\"language\":\"en-US\",\"defaultCatchDocument\":\"default\",\"URL\":[],\"ScriptUrl\":[],\"E164Address\":[],\"SAP\":[],\"Resource\":[],\"Property\":[{\"name\":\"cdr.destinations\",\"value\":\"file://cdr\"}],\"CcxmlProperty\":[{\"name\":\"cdr.destinations\",\"value\":\"file://cdr\"}],\"AudioService\":[],\"TTSLangPrefs\":{\"Language\":[]}}}";
		String str="{\"ServiceGroup\":{\"name\":\"test\",\"parent\":\"default\",\"desc\":\"\",\"serviceGroupProperties\":{\"Base\":{\"Property\":[{\"name\":\"cdr.destinations\",\"value\":\"ert\"}]},\"Vxml\":{\"Property\":[]}}}}";
		//	String str="{\"CcxmlService\":{\"FailOverScriptUrl\":\"\",\"name\":\"cc12\",\"desc\":\"\",\"parent\":\"default\",\"E164Address\":[],\"SAP\":[],\"ScriptUrl\":[\"file:///etc/opt/OC/ocmp/ccxml/scripts/voicexml20.ccxml\"],\"Property\":[],\"LoadSharingPolicy\":\"Priority\",\"VXMLInterpreterNeeded\":false,\"CDRDestination\":[\"file://cdr\"],\"CDREncoding\":\"\",\"Enabled\":\"true\"}}";
		try {
			System.out.println("keyExists :"+returnkeyValueExists(new JSONObject(str),"CDRDestination"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static boolean compareTwoStrings(String[] s1, String[] s2) {
		int n=-1;
		if (s1 == null && s2 == null)
			return true;
		if(s1!=null)
			n = s1.length;
		if(s2!=null && n != s2.length)
			return false;

		for (int i = 0; i < n; i++) {
			if (!s1[i].equals(s2[i]))
				return false;
		}

		return true;
	}
	public static boolean keyExists(JSONObject  object, String searchedKey) {
	    boolean exists = object.has(searchedKey);
	    if(!exists) {      
	        Iterator<?> keys = object.keys();
	        while( keys.hasNext() ) {
	            String key = (String)keys.next();
	            try {
					if ( object.get(key) instanceof JSONObject ) {
					        exists = keyExists((JSONObject) object.get(key), searchedKey);
					}
				} catch (JSONException e) {
					System.err.println("Parsing JSON exception.");
				}
	        }
	    }
	    return exists;
	}
	
	public static String returnkeyValueExists(JSONObject  object, String searchedKey) {
	    boolean exists = false;
	    String val = null;
		try {
//			val = String.valueOf(object.optJSONObject("VxmlService").getJSONArray("Property").length());
//			System.out.println(val);
//			val = String.valueOf(object.optJSONObject("VxmlService").getJSONArray("Property").length());
//			System.out.println(val);
			Object obj = object.optJSONObject("ServiceGroup").optJSONObject("serviceGroupProperties").optJSONObject("Base").getJSONArray("Property");
			if(obj instanceof JSONArray)
			{
				JSONArray jArray = (JSONArray)obj;
			for(int i=0;i<jArray.length();i++)
			{
				JSONObject jo = jArray.getJSONObject(i);
				if(jo.getString("name").equals("cdr.destinations")){
					if(jo.getString("value").length()>0){
						exists=true;
					}
				}
			}
			}
			System.out.println(val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(exists);
	    return val;
	}
}
