import java.util.Vector;

class Graph {
    /**    
    public static void examine(int[] map){
	boolean isCnd = true; boolean isEnd = true;
	boolean isEEnd = true; boolean bijective = true; boolean isI;
	boolean isHEnd = false; boolean herehend;
	boolean isLEnd = false; boolean herelend;
	boolean isQEnd = false; boolean hereqend;
	for (int a = 0; a<vertices; a++){
	    for (int b = 0; b<vertices; b++){
		if ((e[map[a]][map[b]] == 1) && !(e[a][b]==1))
		    isCnd = false;
		if (!(e[map[a]][map[b]] == 1) && (e[a][b]==1))
		    isEnd = false;
		if (!(e[map[a]][map[b]] == 1) && !(map[a]==map[b]) && (e[a][b]==1))
		    isEEnd = false;
		for (int i=0; i<vertices; i++) {
		    isI=false;
		    for (int j=0; j<vertices; j++) {
			if (i==map[j]) isI=true;
		    }
		    if (isI==false) bijective = false;		
		}
	    }
	}
	isHEnd = isHEnd(map);isLEnd = isLEnd(map);isQEnd = isQEnd(map);
	int[] smap = new int[vertices];
	for (int i=0; i<vertices; i++) {
	    smap[i]=map[i];
	}
	if (isCnd) cnd.add(smap); if (isEnd) end.add(smap);
	if (isEEnd) eend.add(smap); if (isEnd && isCnd) send.add(smap);
	if (isCnd & isEEnd) seend.add(smap); 
	if (bijective && isEnd && isCnd) aut.add(smap);
	if (isHEnd) hend.add(smap);
	if (isLEnd) lend.add(smap);
	if (isQEnd) qend.add(smap);
    }
 
    public static void test() {
	int[] map, map1, map2;
	System.out.println("\\\\HEnd Abgeschlossen?\\\\");
	weiter1:
	 for (int i=0; i< hend.size(); i++) {
	     for (int j=0; j< hend.size(); j++) {
 		if (!(isHEnd(comp((int[])hend.get(i),(int[])hend.get(j))))) {
 		    map1 = (int[])hend.get(i);
 		    map2 = (int[])hend.get(j);
 		    map = comp((int[])hend.get(i),(int[])hend.get(j));
 		    print(map1); 
		    System.out.print("$\\bullet$");print(map2);
		    System.out.print("$=$");print(map);
		    break weiter1;
 		}
 	    }
 	}
	System.out.println("\\\\LEnd Abgeschlossen?\\\\");
	weiter2:
	for (int i=0; i< lend.size(); i++) {
	    for (int j=0; j< lend.size(); j++) {
		if (!(isLEnd(comp((int[])lend.get(i),(int[])lend.get(j))))) {
		    map1 = (int[])lend.get(i);
		    map2 = (int[])lend.get(j);
		    map = comp((int[])lend.get(i),(int[])lend.get(j));
 		    print(map1); 
		    System.out.print("$\\bullet$");print(map2);
		    System.out.print("$=$");print(map);
		    break weiter2;
		}
	    }
	}
	System.out.println("\\\\QEnd Abgeschlossen?\\\\");
	weiter3:
	for (int i=0; i< qend.size(); i++) {
	    for (int j=0; j< qend.size(); j++) {
		if (!(isQEnd(comp((int[])qend.get(i),(int[])qend.get(j))))) {
		    map1 = (int[])qend.get(i);
		    map2 = (int[])qend.get(j);
		    map = comp((int[])qend.get(i),(int[])qend.get(j));
 		    print(map1); 
		    System.out.print("$\\bullet$");print(map2);
		    System.out.print("$=$");print(map);
		    break weiter3;
		}
	    }
	}
    }
    **/

}
