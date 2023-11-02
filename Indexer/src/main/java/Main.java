public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Tiempo inicial

        Controller controller = new Controller();
        controller.controller();

        long endTime = System.currentTimeMillis(); // Tiempo final
        long totalTime = endTime - startTime; // Tiempo total en milisegundos
        System.out.println("Tiempo que tardó: " + totalTime + " milisegundos.");
    }
}
