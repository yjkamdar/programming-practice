public class Solution {
    Map<String,List<String>> wordMap=new HashMap<>();
    Map<String,Boolean> visited=new HashMap<>();
    
    public int solve(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        wordList.add(beginWord);
        Map<String,List<String>> wordMap = generateWordMap(wordList);
        //System.out.println(wordMap);
        return getLadderLength(beginWord,endWord,0);
        
    }
    
    class WordObj{
        WordObj(String word,int hops){
            this.word=word;
            this.hops=hops;
        }
        String word;
        int hops;
    }
    
    public int getLadderLength(String bW, String eW, int hops){
        
        Queue<WordObj> words=new LinkedList<>();
        words.add(new WordObj(bW,0));
        while(!words.isEmpty()){
            WordObj curr=words.remove();
            if(curr.word.equals(eW)){
                return curr.hops+1;
            }
            for(String adj:wordMap.get(curr.word)){
                if(visited.get(adj)){
                    continue;
                }
                visited.put(adj,true);
                words.add(new WordObj(adj,curr.hops+1));
            }
        }
        
        return 0;
            
    }
    
    public Map<String,List<String>> generateWordMap(List<String> wordList){
        
        for(String key:wordList){
            wordMap.put(key,new ArrayList<>());
            visited.put(key,false);
        } 
           
        for(int i = 0; i < wordList.size(); ++i){
            String key = wordList.get(i);
            List<String> adjList=wordMap.get(key);
            for(int j = i+1; j < wordList.size(); ++j){
                String adj = wordList.get(j);
                if(singleCharDiff(key,adj)){
                    adjList.add(adj);
                    wordMap.get(adj).add(key);
                }
            }
        }
        return wordMap;
    }
    
    public boolean singleCharDiff(String str1, String str2){
        int diff=0;
        for(int i=0;i<str1.length();i++){
            if(str1.charAt(i)!=str2.charAt(i)){
                if(diff==1)
                    return false;
                diff++;
            }
        }
        return true;
    }

}
