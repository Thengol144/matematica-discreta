import java.lang.AssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes annotats amb "// TODO". L'enunciat de
 * cada un d'ells és al comentari de la seva signatura i exemples del seu funcionament als mètodes
 * `Tema1.tests`, `Tema2.tests`, etc.
 *
 * L'avaluació consistirà en:
 *
 * - Si el codi no compila, la nota del grup serà un 0.
 *
 * - Si heu fet cap modificació que no sigui afegir un mètode, afegir proves als mètodes "tests()" o
 *   implementar els mètodes annotats amb "// TODO", la nota del grup serà un 0.
 *
 * - Principalment, la nota dependrà del correcte funcionament dels mètodes implemnetats (provant
 *   amb diferents entrades).
 *
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . Algunes
 *   consideracions importants:
 *    + Entregau amb la mateixa codificació (UTF-8) i finals de línia (LF, no CR+LF)
 *    + Indentació i espaiat consistent
 *    + Bona nomenclatura de variables
 *    + Declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *      declaracions).
 *    + Convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for (int i = 0; ...))
 *      sempre que no necessiteu l'índex del recorregut.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni qualificar classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 10.
 * Per entregar, posau a continuació els vostres noms i entregau únicament aquest fitxer.
 * - Nom 1: Ibrahim Zougaghi Sabir
 * - Nom 2: Joan Roldán Delgado
 * - Nom 3:
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat i vos recomanam que treballeu amb un fork d'aquest repositori per seguir més
 * fàcilment les actualitzacions amb enunciats nous. Si no podeu visualitzar bé algun enunciat,
 * assegurau-vos de que el vostre editor de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   *
   * La majoria dels mètodes reben de paràmetre l'univers (representat com un array) i els predicats
   * adients (per exemple, `Predicate<Integer> p`). Per avaluar aquest predicat, si `x` és un
   * element de l'univers, podeu fer-ho com `p.test(x)`, que té com resultat un booleà (true si
   * `P(x)` és cert). Els predicats de dues variables són de tipus `BiPredicate<Integer, Integer>` i
   * similarment s'avaluen com `p.test(x, y)`.
   *
   * En cada un d'aquests exercicis (excepte el primer) us demanam que donat l'univers i els
   * predicats retorneu `true` o `false` segons si la proposició donada és certa (suposau que
   * l'univers és suficientment petit com per poder provar tots els casos que faci falta).
   */
  static class Tema1 {
    /*
     * Donat n > 1, en quants de casos (segons els valors de veritat de les proposicions p1,...,pn)
     * la proposició (...((p1 -> p2) -> p3) -> ...) -> pn és certa?
     *
     * Vegeu el mètode Tema1.tests() per exemples.
     */
    static int exercici1(int n) {

        int casos=(int)Math.pow(2,n);
        int [] secuencia=new int[n];
        int numCiertas=0;
        boolean valorActual=true;


        for(int i=0; i<casos; i++){
            
            valorActual=true;
            int i0=i;
            
            for(int j=n-1; j>=0; j--){
                secuencia[j]= (i0&1)==1 ? 1:0;
                i0>>=1; 
            }

            for(int j=0; j<n; j++){

                if((!valorActual)||((secuencia[j]==1)&&(valorActual))){
                    valorActual=true;
                }else{
                    valorActual=false;
                }  
            }


            if(valorActual){
                numCiertas++;
            }
        }
      
        return numCiertas;
    }

    /*
     * És cert que ∀x : P(x) -> ∃!y : Q(x,y) ?
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {

        boolean existeY=false;

        for(int x:universe){

            existeY=false;
        
            if(p.test(x)){

                for(int y:universe){
                
                    if(q.test(x,y)){
                    
                        if(!existeY){
                            existeY=true;
                        }else{
                            return false;
                        }  
                    
                    }  
                }

                if(!existeY){
                    return false;
                }  

            }  
        }  
      
      
        return true; // TODO
    }

    /*
     * És cert que ∃x : ∀y : Q(x, y) -> P(x) ?
     */
    static boolean exercici3(int[] universe, Predicate<Integer> p, BiPredicate<Integer, Integer> q) {

        boolean xValida=true;
      
        for(int x:universe){
            xValida=true;
          
            for(int y:universe){

                if(!q.test(x,y)){
                    xValida=false;
                    break;
                }  
            }

            if(xValida){

                if(p.test(x)){
                    return true;
                }else{
                    return false;
                }
            } 
        }
      
      
        return true; // TODO
    }

    /*
     * És cert que ∃x : ∃!y : ∀z : P(x,z) <-> Q(y,z) ?
     */
    static boolean exercici4(int[] universe, BiPredicate<Integer, Integer> p, BiPredicate<Integer, Integer> q) {
      
        for(int x:universe){
            int cantY=0;
        
            for(int y:universe){
                boolean todaZ=true;
            
                for(int z:universe){

                    if(p.test(x,z)!=q.test(y,z)){
                        todaZ=false;
                        break;
                    }
                }

                if(todaZ){
                    cantY++;
                    
                    if(cantY>1){
                        break;
                    }
                }
            }

            if(cantY==1){
                return true;
            } 
        }
      
        return false; // TODO
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1

      // p1 -> p2 és cert exactament a 3 files
      // p1 p2
      // 0  0  <-  
      // 0  1  <-
      // 1  0
      // 1  1  <-
      assertThat(exercici1(2) == 3);

      // (p1 -> p2) -> p3 és cert exactament a 5 files
      // p1 p2 p3
      // 0  0  0
      // 0  0  1  <-
      // 0  1  0
      // 0  1  1  <-
      // 1  0  0  <-
      // 1  0  1  <-
      // 1  1  0
      // 1  1  1  <-
      assertThat(exercici1(3) == 5);

      // Exercici 2
      // ∀x : P(x) -> ∃!y : Q(x,y)
      assertThat(
          exercici2(
            new int[] { 1, 2, 3 },
            x -> x % 2 == 0,
            (x, y) -> x+y >= 5
          )
      );

      assertThat(
          !exercici2(
            new int[] { 1, 2, 3 },
            x -> x < 3,
            (x, y) -> x-y > 0
          )
      );

      // Exercici 3
      // És cert que ∃x : ∀y : Q(x, y) -> P(x) ?
      assertThat(
          exercici3(
            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            x -> x % 3 != 0,
            (x, y) -> y % x == 0
          )
      );

      assertThat(
          exercici3(
            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            x -> x % 4 != 0,
            (x, y) -> (x*y) % 4 != 0
          )
      );

      // Exercici 4
      // És cert que ∃x : ∃!y : ∀z : P(x,z) <-> Q(y,z) ?
      assertThat(
          exercici4(
            new int[] { 0, 1, 2, 3, 4, 5 },
            (x, z) -> x*z == 1,
            (y, z) -> y*z == 2
          )
      );

      assertThat(
          !exercici4(
            new int[] { 2, 3, 4, 5 },
            (x, z) -> x*z == 1,
            (y, z) -> y*z == 2
          )
      );
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][].
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. Per exemple
   *   int[][] rel = {{0,0}, {0,1}, {1,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   * Als tests utilitzarem extensivament la funció generateRel definida al final (també la podeu
   * utilitzar si la necessitau).
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam o bé amb el seu
   * graf o amb un objecte de tipus Function<Integer, Integer>. Sempre donarem el domini int[] a, el
   * codomini int[] b. En el cas de tenir un objecte de tipus Function<Integer, Integer>, per aplicar
   * f a x, és a dir, "f(x)" on x és d'A i el resultat f.apply(x) és de B, s'escriu f.apply(x).
   */
  static class Tema2 {
    /*
     * Calculau el nombre d'elements del conjunt de parts de (a u b) × (a \ c)
     *
     * Podeu soposar que `a`, `b` i `c` estan ordenats de menor a major.
     */
    static int exercici1(int[] a, int[] b, int[] c) {

        int m=a.length;
        int n=b.length;
        int p=c.length;
        
        //Cálculo A\C
        int [] a_c=new int[m];
        int j=0;
        for(int i=0;i<m;i++){
            
            while(j<p&&c[j]<a[i]){
                j++;
            }
            
            if(j==p || c[j]>a[i]){
                a_c[i]=a[i];
            }
        }
        
        
        //Calculamos A U B
        int [] aUb=new int[m+n];
        int r=0, s=0, t=0;
        while(r<m && s<n){
            
            if(a[r] < b[s]){
                aUb[t++]=a[r++];
            }else if(a[r] > b[s]){
                aUb[t++]=b[s++];
            }else{
                aUb[t++]=a[r++];
                s++;
            }
        }
        
        while(r<m){
            aUb[t++]=a[r++];
        }
        
        while(s<n){
            aUb[t++]=b[s++];
        }
        
        
        //Calculamos conjunto de partes
        int contador=0;
        for(int i=0; i<(1<<(m+n)); i++){
            boolean valido=true;
            
            for(int x:a_c){
                
                if((i&(1<<x)) == 0){
                    valido=false;
                    break;
                }
            }
            
            if(valido){
                contador++;
            }
        }
        
        
        return contador; // TODO
    }

    /*
     * La clausura d'equivalència d'una relació és el resultat de fer-hi la clausura reflexiva, simètrica i
     * transitiva simultàniament, i, per tant, sempre és una relació d'equivalència.
     *
     * Trobau el cardinal d'aquesta clausura.
     *
     * Podeu soposar que `a` i `rel` estan ordenats de menor a major (`rel` lexicogràficament).
     */
    static int exercici2(int[] a, int[][] rel) {

        boolean[][] clausura=new boolean[a.length][a.length];
      
        for(int[]par : rel){
          
            int i=indexDe(a, par[0]);
            int j=indexDe(a, par[1]);
            clausura[i][j]=true;
        }
      
        //Aplicamos clausura reflexiva
        for(int i=0; i<a.length; i++){
            clausura[i][i]=true;
        }
      
        //Aplicamos clausura simétrica
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
              
                if(clausura[i][j]){
                    clausura[j][i]=true;
                }
            }
        }
      
        //Aplicamos clausura transitiva
        for(int k=0; k<a.length; k++){
            for(int i=0; i<a.length; i++){
                for(int j=0; j<a.length; j++){
                  
                    if(clausura[i][k]&&clausura[k][j]){
                        clausura[i][j]=true;
                    }
                }
            }
        }
      
      
        //Contabilizamos los componentes de la clausura
        int contador=0;
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
              
                if(clausura[i][j]){
                    contador++;
                }
            }
        }
        
      
        return contador; // TODO   
    }

    /*
     * Comprovau si la relació `rel` és un ordre total sobre `a`. Si ho és retornau el nombre
     * d'arestes del seu diagrama de Hasse. Sino, retornau -2.
     *
     * Podeu soposar que `a` i `rel` estan ordenats de menor a major (`rel` lexicogràficament).
     */
    static int exercici3(int[] a, int[][] rel) {

        if(!esOrdenTotal(a, rel)){
            return -2;
        }else{
            int numAristas=construccionHasse(a, rel);
            return numAristas;
        }
    }

    static boolean esOrdenTotal(int []a, int[][] rel){
        
        int n = a.length;
        boolean[][] matrizAdy=new boolean[n][n];
        
        for (int[] par:rel) {
            
            int x=par[0];
            int y=par[1];
            matrizAdy[x][y]=true;
        }

        //Verificamos si es reflexiva
        for (int i=0; i<n; i++) {
            
            if (!matrizAdy[a[i]][a[i]]) {
                return false;
            }
        }

        //Verificamos si es antisimétrica y transitiva
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                
                int x=a[i];
                int y=a[j];
                if (x==y) continue;

                //Verificamos si es antisimétrica
                if (matrizAdy[x][y] && matrizAdy[y][x] && x!=y) {
                    return false;
                }

                //Verificamos si es transitiva
                for (int k=0; k<n; k++) {
                    int z=a[k];
                    
                    if (matrizAdy[x][y] && matrizAdy[y][z] && !matrizAdy[x][z]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    static int construccionHasse(int[] a, int[][] rel){
        
        int n=a.length;
        boolean[][] matriz=new boolean[n][n];
        
        
        for(int[] par:rel){
            
            int x=par[0];
            int y=par[1];
            matriz[x][y]=true;
        }

        
        int numAristes=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i!=j && matriz[i][j]){
                    boolean esMinima=true;
                    
                    for(int k=0; k<n; k++){
                        
                        if(k!=i && k!=j && matriz[i][k] && matriz[k][j]) {
                            esMinima=false;
                            break;
                        }
                    }
                    
                    if(esMinima){
                        numAristes++;
                    }
                }
            }
        }

        return numAristes;  
    }


    private static int indexDe(int[] a, int x) {
      for (int i = 0; i < a.length; i++) {
          if (a[i] == x) {
              return i;
          }
      }
      return -1;
    }
    
    

    /*
     * Comprovau si les relacions `rel1` i `rel2` són els grafs de funcions amb domini i codomini
     * `a`. Si ho son, retornau el graf de la composició `rel2 ∘ rel1`. Sino, retornau null.
     *
     * Podeu soposar que `a`, `rel1` i `rel2` estan ordenats de menor a major (les relacions,
     * lexicogràficament).
     */
    static int[][] exercici4(int[] a, int[][] rel1, int[][] rel2) {
        
        int n=a.length;
        boolean[][] matriz1=new boolean[n][n];
        boolean[][] matriz2=new boolean[n][n];

        //Construimos las matrices de las relaciones
        for (int[] par:rel1) {
            int i=indexDe(a, par[0]);
            int j=indexDe(a, par[1]);
            matriz1[i][j]=true;
        }

        for (int[] par:rel2) {
            int i=indexDe(a, par[0]);
            int j=indexDe(a, par[1]);
            matriz2[i][j]=true;
        }

        //Verificamos si son grafos de funciones
        if (!esGrafoDeFuncion(matriz1) || !esGrafoDeFuncion(matriz2)) {
            return null;
        }

        //Calculamos la composición de las relaciones
        boolean[][] comp=new boolean[n][n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                for (int k=0; k<n; k++){
                    
                    if (matriz1[i][k] && matriz2[k][j]) {
                        comp[i][j]=true;
                        break;
                    }
                }
            }
        }

        //Convertimos la matriz de la composición en un array de pares
        List<int[]> compList=new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(comp[i][j]){
                    compList.add(new int[]{a[i], a[j]});
                }
            }
        }

        return compList.toArray(new int[0][0]);
    }


    private static boolean esGrafoDeFuncion(boolean[][] matriz){
        
        for (int i=0; i<matriz.length; i++) {
            int count=0;
            
            for (int j=0; j<matriz.length; j++) {
                
                if (matriz[i][j]) {
                    count++;
                }
            }
            
            if (count!=1) {
                return false;
            }
        }
        return true;
    }

    
    /*
     * Comprovau si la funció `f` amb domini `dom` i codomini `codom` té inversa. Si la té, retornau
     * el seu graf (el de l'inversa). Sino, retornau null.
     */
    static int[][] exercici5(int[] dom, int[] codom, Function<Integer, Integer> f) {
        
        if (esBiyectiva(dom, codom, f)) {
            int[][] graficoInversa = new int[codom.length][2];
            
            for (int i=0; i<codom.length; i++) {
                
                int y=codom[i];
                int x=encontrarPreimagen(y, dom, f);
                graficoInversa[i][0]=y;
                graficoInversa[i][1]=x;
            }
            
            return graficoInversa;
            
        }else {
            return null;
        }
    }


    static boolean esBiyectiva(int[] dom, int[] codom, Function<Integer, Integer> f){
        
        boolean[] codominioImagen = new boolean[codom.length];
      
        //Verificar que cada x solo tiene un y en el codominio
        for (int x:dom){
            
            int y=f.apply(x);
            int indiceX=encontrarIndice(codom, y);
        
            if (indiceX==-1 || codominioImagen[indiceX]){
                return false; 
            }
        
            codominioImagen[indiceX]=true;
        }
      
        //Verificar que todos los elementos tengan una imagen
        for (boolean imagen:codominioImagen) {
        
            if (!imagen) {
                return false;
            }
        }

        return true;
    }


    static int encontrarIndice(int[] arr, int elemento){
      
        for (int i=0; i<arr.length; i++) {
        
            if (arr[i]==elemento) {
                return i;
            }
        }
        
        return -1;
    }


    static int encontrarPreimagen(int y, int[] dom, Function<Integer, Integer> f){
      
        for (int x:dom) {
        
            if (f.apply(x)==y) {
            return x;
            }
        }
      
        return -1;
    }

    
    
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // |(a u b) × (a \ c)|

      assertThat(
          exercici1(
            new int[] { 0, 1, 2 },
            new int[] { 1, 2, 3 },
            new int[] { 0, 3 }
          )
          == 8
      );

      assertThat(
          exercici1(
            new int[] { 0, 1 },
            new int[] { 0 },
            new int[] { 0 }
          )
          == 2
      );

      // Exercici 2
      // nombre d'elements de la clausura d'equivalència

      final int[] int08 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

      assertThat(exercici2(int08, generateRel(int08, (x, y) -> y == x + 1)) == 81);

      final int[] int123 = { 1, 2, 3 };

      assertThat(exercici2(int123, new int[][] { {1, 3} }) == 5);

      // Exercici 3
      // Si rel és un ordre total, retornar les arestes del diagrama de Hasse

      final int[] int05 = { 0, 1, 2, 3, 4, 5 };

      assertThat(exercici3(int05, generateRel(int05, (x, y) -> x >= y)) == 5);
      assertThat(exercici3(int08, generateRel(int05, (x, y) -> x <= y)) == -2);

      // Exercici 4
      // Composició de grafs de funcions (null si no ho son)

      assertThat(
          exercici4(
            int05,
            generateRel(int05, (x, y) -> x*x == y),
            generateRel(int05, (x, y) -> x == y)
          )
          == null
      );


      var ex4test2 = exercici4(
          int05,
          generateRel(int05, (x, y) -> x + y == 5),
          generateRel(int05, (x, y) -> y == (x + 1) % 6)
        );

      assertThat(
          Arrays.deepEquals(
            lexSorted(ex4test2),
            generateRel(int05, (x, y) -> y == (5 - x + 1) % 6)
          )
      );

      // Exercici 5
      // trobar l'inversa (null si no existeix)

      assertThat(exercici5(int05, int08, x -> x + 3) == null);

      assertThat(
          Arrays.deepEquals(
            lexSorted(exercici5(int08, int08, x -> 8 - x)),
            generateRel(int08, (x, y) -> y == 8 - x)
          )
      );
    }

    /**
     * Ordena lexicogràficament un array de 2 dimensions
     * Per exemple:
     *  arr = {{1,0}, {2,2}, {0,1}}
     *  resultat = {{0,1}, {1,0}, {2,2}}
     */
    static int[][] lexSorted(int[][] arr) {
      if (arr == null)
        return null;

      var arr2 = Arrays.copyOf(arr, arr.length);
      Arrays.sort(arr2, Arrays::compare);
      return arr2;
    }

    /**
     * Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que satisfàn pred.test(a, b)
     * Per exemple:
     *   as = {0, 1}
     *   bs = {0, 1, 2}
     *   pred = (a, b) -> a == b
     *   resultat = {{0,0}, {1,1}}
     */
    static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
      var rel = new ArrayList<int[]>();

      for (int a : as) {
        for (int b : bs) {
          if (pred.test(a, b)) {
            rel.add(new int[] { a, b });
          }
        }
      }

      return rel.toArray(new int[][] {});
    }

    /// Especialització de generateRel per a = b
    static int[][] generateRel(int[] as, BiPredicate<Integer, Integer> pred) {
      return generateRel(as, as, pred);
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Els (di)grafs vendran donats com llistes d'adjacència (és a dir, tractau-los com diccionaris
   * d'adjacència on l'índex és la clau i els vèrtexos estan numerats de 0 a n-1). Per exemple,
   * podem donar el graf cicle d'ordre 3 com:
   *
   * int[][] c3dict = {
   *   {1, 2}, // veïns de 0
   *   {0, 2}, // veïns de 1
   *   {0, 1}  // veïns de 2
   * };
   *
   * **NOTA: Els exercicis d'aquest tema conten doble**
   */
  static class Tema3 {
    /*
     * Determinau si el graf és connex. Podeu suposar que `g` no és dirigit.
     */
    static boolean exercici1(int[][] g) {
        
        int n=g.length;
        boolean [] pasados= new boolean[n];
        
        dfs(0,pasados,g);
        
        for(boolean verticePasado:pasados){
            
            if(!verticePasado){
                return false;
            }
        }
        
        return true; // TO DO
    }


    static void dfs(int vertice, boolean [] pasado, int[][]grafo){
        
        pasado[vertice]=true;
        
        for(int adyacente:grafo[vertice]){
            if(!pasado[adyacente]){
                dfs(adyacente, pasado, grafo);
            }
        }
    }

    
    /*
     * Donat un tauler d'escacs d'amplada `w` i alçada `h`, determinau quin és el mínim nombre de
     * moviments necessaris per moure un cavall de la casella `i` a la casella `j`.
     *
     * Les caselles estan numerades de `0` a `w*h-1` per files. Per exemple, si w=5 i h=2, el tauler
     * és:
     *   0 1 2 3 4
     *   5 6 7 8 9
     *
     * Retornau el nombre mínim de moviments, o -1 si no és possible arribar-hi.
     */
    static int exercici2(int w, int h, int i, int j) {

        int[][]posiciones={{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {-1, -2}, {1, -2}};
        
        //Si la posicion inicial del caballo retornamos un 0
        if(j==i) return 0;
        
        //Creamos una matriz recorrido
        int[][]recorrido=new int[w][h];
        //Inicializamos una array f para recorrer la longitud
        //de la matriz recorrido
        for(int[]f:recorrido){
            //Llenamos el array f con -1
            Arrays.fill(f,-1);
        }
        
        //Inicializar las cordenadas iniciales y finales del
        //eje X e Y
        
        int Xinicial=i%w;
        int Yinicial=i/w;
        int Xfinal=j%w;
        int Yfinal=j/w;
        
        //Creamos una matriz booleana de la misma que de recorridos 
        //y inicializamos las casillas
        
        boolean [][]pasadas=new boolean[w][h];
        //inicializamos las matrices en la posicion inicial
        recorrido[Xinicial][Yinicial]=0;
        pasadas[Xinicial][Yinicial]=true;
        
        //Entramos en un bucle restringido por un booleano
        //para procesar todas las celdas
        boolean procesar=false;
        while(!procesar){
            procesar=true;
            // Itera sobre cada columna
                for (int p = 0; p < w; p++) {
                    // Itera sobre cada fila
                    for (int k = 0; k < h; k++) {
                        // Comprueba si la celda ha sido procesada
                        if (recorrido[p][k] != -1) {
                            // Realiza el movimiento desde la celda actual
                            for (int[] movimiento : posiciones) {
                                int nuevoX = p + movimiento[0];
                                int nuevoY = k + movimiento[1];
                                // Comprueba que la nueva posición es válida y no ha sido visitada
                                if (esValido(nuevoX, nuevoY, w, h) && !pasadas[nuevoX][nuevoY]) {
                                    int nuevaDistancia = recorrido[p][k] + 1;
                                    // Actualiza la distancia si es mejor que la existente
                                    if (recorrido[nuevoX][nuevoY] == -1 || nuevaDistancia < recorrido[nuevoX][nuevoY]) {
                                        recorrido[nuevoX][nuevoY] = nuevaDistancia;
                                        pasadas[nuevoX][nuevoY] = true;
                                        procesar = false; // Indica que hay que seguir iterando
                                    }
                                }
                            }
                        }
                    }
                }

        }
        
        
        
        return recorrido[Xfinal][Yfinal];
    }


    static boolean esValido(int x, int y, int w, int h) {
        return x >= 0 && x < w && y >= 0 && y < h;
    }

    
    /*
     * Donat un arbre arrelat (graf dirigit `g`, amb arrel `r`), decidiu si el vèrtex `u` apareix
     * abans (o igual) que el vèrtex `v` al recorregut en preordre de l'arbre.
     */
    static boolean exercici3(int[][] g, int r, int u, int v) {

        int [] preorden=new int[g.length];
        int [] indice={0};
        
        Arrays.fill(preorden,-1);
        
        preOrden(r, g, indice, preorden);
        
        return preorden[v]>=preorden[u]; // TO DO
    }


    private static void preOrden(int vertice, int[][] grafo, int[]indice, int[] preorden){
        
        preorden[vertice]= indice[0]++;
        
        for(int adyacente: grafo[vertice]){
            
            if(preorden[adyacente]==-1){
                preOrden(adyacente, grafo, indice, preorden);
            }
        }
    }

    
    /*
     * Donat un recorregut en preordre (per exemple, el primer vèrtex que hi apareix és `preord[0]`)
     * i el grau de cada vèrtex (per exemple, el vèrtex `i` té grau `d[i]`), trobau l'altura de
     * l'arbre corresponent.
     *
     * L'altura d'un arbre arrelat és la major distància de l'arrel a les fulles.
     */
    static int exercici4(int[] preord, int[] d) {

        //Para poder proceder creamos un objeto árbol
        Arbol arbol=new Arbol();
        
        Vertice raiz=arbol.crearArbol(preord, d);
        
        return arbol.calcularAltura(raiz);
    }


    static class Vertice{
        int valor;
        List<Vertice> hijos;
        
        public Vertice(int a){
            
            this.valor=a;
            this.hijos=new ArrayList<>();
        }
        
        public void agregarhijos(Vertice hijo){
            hijos.add(hijo);
        }
        
    }


    static class Arbol{
        //Retornamos un vertice
        Vertice raiz;
        
        public Vertice crearArbol(int []preord, int[] d){
            
            int [] indice={0};
            return construir(preord, d, indice);
        }
    
        private Vertice construir(int[] preord, int[] d,int[] indice){
        
            if(indice[0]>=preord.length){
                return null;
            }
        
            Vertice vertice=new Vertice(preord[indice[0]]);
        
            int numhijos=d[vertice.valor];
            indice[0]++;
            for(int i=0;i<numhijos;i++){
                vertice.agregarhijos(construir(preord,d,indice));
            }
            
            return vertice;
        }
    
        public int calcularAltura(Vertice vertice){
            
            if(vertice==null){
                return -1;
            }
        
            int maximaAltura=-1;
            for( Vertice hijo:vertice.hijos){
                int alturaActual=calcularAltura(hijo);
                
                if(maximaAltura<alturaActual){
                    maximaAltura=alturaActual;
                }
            }
            
            return maximaAltura +1;
        }
    }
    

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // G connex?

      final int[][] B2 = { {}, {} };

      final int[][] C3 = { {1, 2}, {0, 2}, {0, 1} };

      final int[][] C3D = { {1}, {2}, {0} };

      assertThat(exercici1(C3));
      assertThat(!exercici1(B2));

      // Exercici 2
      // Moviments de cavall

      // Tauler 4x3. Moviments de 0 a 11: 3.
      // 0  1   2   3
      // 4  5   6   7
      // 8  9  10  11
      assertThat(exercici2(4, 3, 0, 11) == 3);

      // Tauler 3x2. Moviments de 0 a 2: (impossible).
      // 0 1 2
      // 3 4 5
      assertThat(exercici2(3, 2, 0, 2) == -1);

      // Exercici 3
      // u apareix abans que v al recorregut en preordre (o u=v)

      final int[][] T1 = {
        {1, 2, 3, 4},
        {5},
        {6, 7, 8},
        {},
        {9},
        {},
        {},
        {},
        {},
        {10, 11},
        {},
        {}
      };

      assertThat(exercici3(T1, 0, 5, 3));
      assertThat(!exercici3(T1, 0, 6, 2));

      // Exercici 4
      // Altura de l'arbre donat el recorregut en preordre

      final int[] P1 = { 0, 1, 5, 2, 6, 7, 8, 3, 4, 9, 10, 11 };
      final int[] D1 = { 4, 1, 3, 0, 1, 0, 0, 0, 0, 2,  0,  0 };

      final int[] P2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
      final int[] D2 = { 2, 0, 2, 0, 2, 0, 2, 0, 0 };

      assertThat(exercici4(P1, D1) == 3);
      assertThat(exercici4(P2, D2) == 4);
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 4 (Aritmètica).
   *
   * En aquest tema no podeu:
   *  - Utilitzar la força bruta per resoldre equacions: és a dir, provar tots els nombres de 0 a n
   *    fins trobar el que funcioni.
   *  - Utilitzar long, float ni double.
   *
   * Si implementau algun dels exercicis així, tendreu un 0 d'aquell exercici.
   */
  static class Tema4 {
    /*
     * Calculau el mínim comú múltiple de `a` i `b`.
     */
    static int exercici1(int a, int b) {
        
        int producto=a*b;
        
        if(producto<0){
            producto=-producto;
        }
        
        return producto/mcd(a,b); // TO DO
    }


    static int mcd(int a, int b){
        
        while(b!=0){
            int auxiliar=b;
            b=a%b;
            a=auxiliar;
        }
        
        return a;
    }
    

    /*
     * Trobau totes les solucions de l'equació
     *
     *   a·x ≡ b (mod n)
     *
     * donant els seus representants entre 0 i n-1.
     *
     * Podeu suposar que `n > 1`. Recordau que no no podeu utilitzar la força bruta.
     */
    static int[] exercici2(int a, int b, int n) {

        // Calcula el MCD de dos números usando el algoritmo de Euclides
        int mcd = euclides(a, n);

        // Si b no es divisible por el MCD, no hay solución
        if (b % mcd != 0) {
            return new int[]{}; // Retorna un array vacío si no hay solución
        }

        // Simplifica los números dividiéndolos por el MCD
        a = a / mcd;
        b = b / mcd;
        n = n / mcd;

        // Calcula la solución utilizando el algoritmo extendido de Euclides
        int[] resultado = extensionEuclides(a, n);
        int x = resultado[0]; // Coeficiente x de la solución
        int y = resultado[1]; // Coeficiente y de la solución

        // Calcula la solución particular para la ecuación lineal
        int mult = y * b; // Multiplicación de y por b
        y = mult % n; // Reduce y módulo n
        if (y < 0) {
            y = y + n; // Ajusta y si es negativo sumándole n
        }

        // Genera las soluciones generales
        int[] soluciones = new int[mcd];
        for (int j = 0; j < mcd; j++) {
            soluciones[j] = (y + j * n) % (n * mcd); // Calcula y almacena las soluciones en el array
        }

        return soluciones; // Retorna las soluciones encontradas
    }


    public static int euclides(int num1, int num2){
        
        while(num2!=0){
            int temp=num2;//Variable temporal para guardar num2
            num2 =num1%num2; //Asigna a num2 el resto de la división de num1 entere num2
            num1=temp;//Asigna a num1 el valor temp 
        }
        return num1;//Devuelve el num1
    }


    public static int[] extensionEuclides(int num1, int num2) {
        
        if (num2 == 0) {
            return new int[]{num1, 1, 0}; // Caso base: si num2 es 0, devuelve [num1, 1, 0]
        }

        // Llamada recursiva con los valores (num2, num1 % num2)
        int[] results = extensionEuclides(num2, num1 % num2);

        // Valores devueltos de la recursión
        int gcd = results[0]; // El MCD
        int coeffX = results[1]; // Coeficiente x de la solución anterior
        int coeffY = results[2]; // Coeficiente y de la solución anterior

        // Actualización de los coeficientes x e y
        int newCoeffX = coeffY; // Nuevo coeficiente x
        int newCoeffY = coeffX - (num1 / num2) * coeffY; // Nuevo coeficiente y

        // Devuelve el MCD y los coeficientes actualizados
        return new int[]{gcd, newCoeffX, newCoeffY};
    }
    
    
    /*
     * Donats `a != 0`, `b != 0`, `c`, `d`, `m > 1`, `n > 1`, determinau si el sistema
     *
     *   a·x ≡ c (mod m)
     *   b·x ≡ d (mod n)
     *
     * té solució.
     */
    static boolean exercici3(int a, int b, int c, int d, int m, int n) {

        if( (c%mcd(a,m)!=0) || (d%mcd(b,n)!=0) ){
            return false;
        }
        
        int[]primeraSolucion=extensionEuclides(a,m);
        int[]segundaSolucion=extensionEuclides(b,n);
        
        int primeraX=(c/mcd(a,m))*primeraSolucion[1];
        int segundaX=(d/mcd(b,n))*segundaSolucion[1];
        

        return ((primeraX%mcd(a,m))==(segundaX%mcd(b,n)%mcd(m,n)));
    }

    /*
     * Donats `n` un enter, `k > 0` enter, i `p` un nombre primer, retornau el residu de dividir n^k
     * entre p.
     *
     * Alerta perquè és possible que n^k sigui massa gran com per calcular-lo directament.
     * De fet, assegurau-vos de no utilitzar cap valor superior a max{n, p²}.
     *
     * Anau alerta també abusant de la força bruta, la vostra implementació hauria d'executar-se en
     * qüestió de segons independentment de l'entrada.
     */
    static int exercici4(int n, int k, int p) {
        //Variables long para Evitar desbordamientos
        long nLong=n;
        long kLong=k;
        long pLong=p;
        
        if(pLong==1){
            return 0;
        }
        
        if(nLong<0){
            nLong=((nLong%pLong)+pLong)%pLong;
        }
      
        long kReducida=kLong%phi(pLong);
        long residuo=exponenciacionModular(nLong, kReducida, pLong);
        
        return (int)residuo;
    }


    static long phi(long p){
        
        long resultado=p;
        
        for(long i=2; i*i<p; i++){
            
            if(p%i==0){
                
                while(p%i==0){
                    p=p/i;
                }
                
                resultado=resultado - (resultado/i);
            }
        }
        
        if(p>1){
            resultado=resultado - (resultado/p);
        }

        return resultado;
    }


    static long exponenciacionModular(long n, long k, long p) {
        
        long resultado=1;
        n=n%p;

        while (k>0){

            if ((k&1)==1){
                resultado=(resultado*n)%p;
            }

            k=k>>1;
            n=(n*n)%p;
        }

        return resultado;
    }
    
    
    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // mcm(a, b)

      assertThat(exercici1(35, 77) == 5*7*11);
      assertThat(exercici1(-8, 12) == 24);

      // Exercici 2
      // Solucions de a·x ≡ b (mod n)

      assertThat(Arrays.equals(exercici2(2, 2, 4), new int[] { 1, 3 }));
      assertThat(Arrays.equals(exercici2(3, 2, 4), new int[] { 2 }));

      // Exercici 3
      // El sistema a·x ≡ c (mod m), b·x ≡ d (mod n) té solució?

      assertThat(exercici3(3, 2, 2, 2, 5, 4));
      assertThat(!exercici3(3, 2, 2, 2, 10, 4));

      // Exercici 4
      // n^k mod p

      assertThat(exercici4(2018, 2018, 5) == 4);
      assertThat(exercici4(-2147483646, 2147483645, 46337) == 7435);
    }
  }

  /**
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `assertThat` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    Tema1.tests();
    Tema2.tests();
    Tema3.tests();
    Tema4.tests();
  }

  /// Si b és cert, no fa res. Si b és fals, llança una excepció (AssertionError).
  static void assertThat(boolean b) {
    if (!b)
      throw new AssertionError();
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
