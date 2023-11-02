public class Test {
    public static void main(String[] args) {
        int filterSize = 1000;  // Tamaño del filtro de Bloom
        int numHashFunctions = 5;  // Número de funciones hash

        BloomFilter bloomFilter = new BloomFilter(filterSize, numHashFunctions);

        // Agregar elementos al filtro de Bloom
        bloomFilter.add("Libro1");
        bloomFilter.add("Libro2");
        bloomFilter.add("Libro3");

        // Verificar la pertenencia de elementos al conjunto
        System.out.println("Libro1 pertenece al conjunto: " + bloomFilter.contains("Libro1"));  // Debería ser true
        System.out.println("Libro4 pertenece al conjunto: " + bloomFilter.contains("Libro100"));  // Debería ser false
    }
}
