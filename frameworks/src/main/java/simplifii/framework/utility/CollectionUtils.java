package simplifii.framework.utility;

import java.util.Collection;

/**
 * Created by robin on 10/18/16.
 */

public class CollectionUtils {

    public static boolean isEmpty(Collection collection){
        return (collection==null||collection.isEmpty())?true:false;
    }

    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }

    public static boolean isEmpty(String str){
        return (str==null||str.isEmpty())?true:false;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}
