public class MyClass {
    public static String longestCommonPrefix(String[] strs) {
        String str="";
        for(int i=0; i<strs[0].length(); i++){
            if(strs[0].length()==i){return str;}
            char temp=strs[0].charAt(i);
            // System.out.println(temp);
            boolean bool=true;
            for(String x: strs){
                if(x.charAt(i)!=temp || x.length()==i){
                    return str;
                }
                
            }
            str+=temp;
            
        }
        return str;
    }
    public static void main(String args[]){
        String[] str = {"flower","flight", "flow"};
        System.out.println(MyClass.longestCommonPrefix(str));
    }
}