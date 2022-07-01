import java.util.*;
import java.io.*;
import java.lang.*;
import com.boomi.execution.ExecutionUtil;

String assignKeyValue(String key, String value) {
    if(value != null && value != "")
        return key+"="+value;
    else
        return "";
}
for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    String query_jiva_member_id = ExecutionUtil.getDynamicProcessProperty("query_jiva_member_id");
    String query_ext_member_id = ExecutionUtil.getDynamicProcessProperty("query_ext_member_id");
    String query_jiva_episode_id = ExecutionUtil.getDynamicProcessProperty("query_jiva_episode_id");
    String query_ext_episode_id = ExecutionUtil.getDynamicProcessProperty("query_ext_episode_id");
    String query_assessment_status_id = ExecutionUtil.getDynamicProcessProperty("query_assessment_status_id");
    String query_limit = ExecutionUtil.getDynamicProcessProperty("query_limit");
    String query_start_index = ExecutionUtil.getDynamicProcessProperty("query_start_index");
    String jiva_member_id = assignKeyValue("jiva_member_id",query_jiva_member_id);
    String ext_member_id = assignKeyValue("ext_member_id",query_ext_member_id);
    String jiva_episode_id = assignKeyValue("jiva_episode_id",query_jiva_episode_id);
    String ext_episode_id = assignKeyValue("ext_episode_id",query_ext_episode_id);
    String assessment_status_id = assignKeyValue("assessment_status_id",query_assessment_status_id);
    String limit = assignKeyValue("limit",query_limit);
    String start_index = assignKeyValue("start_index",query_start_index);
    List<String> words = new ArrayList<String>(Arrays.asList(jiva_member_id, ext_member_id, jiva_episode_id, ext_episode_id, assessment_status_id, limit, start_index));
    words.removeAll(Arrays.asList("", null));
    String concatenatedString = "";
    String delimiter = "&";
    for (String word : words) {
        concatenatedString += concatenatedString.equals("") ? word : delimiter + word;
    }
    String final_query = concatenatedString;
    ExecutionUtil.setDynamicProcessProperty("final_query", final_query, false);
    dataContext.storeStream(is, props);
}
