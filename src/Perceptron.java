import java.io.*;
import java.util.*;
public class Perceptron {
    private final double[] weights;
    private double threshold;
    private final double learningRate;
    private final List<double[]> trainingData;
    private final List<double[]> testData;
    public Perceptron(int numbers, double learningRate) {
        this.weights = new double[numbers];
        this.threshold = 0.0;
        this.learningRate = learningRate;
        this.trainingData = new ArrayList<>();
        this.testData = new ArrayList<>();
        setWeights();
    }
    private void setWeights() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random() * 0.1) - 0.05;
        }
    }
    public void train(String trainDataPath, int epochs) throws IOException {
        loadData(trainDataPath, trainingData);
        for (int epoch = 0; epoch < epochs; epoch++) {
            Collections.shuffle(trainingData);
            for (double[] instance : trainingData) {
                double predicted = predict(instance);
                double target = instance[instance.length - 1];
                double error = target - predicted;
                for (int i = 0; i < weights.length; i++) {
                    weights[i] += learningRate * error * instance[i];
                }
                threshold += learningRate * error;
            }
            System.out.println("Epoch " + (epoch + 1) + " completed");
            test();
        }
    }
    public void train2(String trainDataPath, int epochs) throws IOException {
        loadData2(trainDataPath, trainingData);
        for (int epoch = 0; epoch < epochs; epoch++) {
            Collections.shuffle(trainingData);
            for (double[] instance : trainingData) {
                double predicted = predict(instance);
                double target;
                if (instance[instance.length - 1] == 1.0) {
                    target = 1.0;
                } else {
                    target = 0.0;
                }
                double error = target - predicted;
                for (int i = 0; i < weights.length; i++) {
                    weights[i] += learningRate * error * instance[i];
                }
                threshold += learningRate * error;
            }
            System.out.println("Epoch " + (epoch + 1) + " completed");
            test2();
        }
    }
    private void loadData(String filePath, List<double[]> data) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                double[] instance = new double[parts.length];
                boolean numValid = true;
                for (int i = 0; i < parts.length; i++) {
                    try {
                        instance[i] = Double.parseDouble(parts[i]);
                    } catch (NumberFormatException e) {
                        numValid = false;
                        break;
                    }
                }
                if (numValid) {
                    data.add(instance);
                }
            }
        }
    }
    private void loadData2(String filePath, List<double[]> data) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(",");
                double[] instance = new double[parts.length - 1];
                boolean numValid = true;
                for (int i = 0; i < parts.length - 1; i++) {
                    try {
                        instance[i] = Double.parseDouble(parts[i]);
                    } catch (NumberFormatException e) {
                        numValid = false;
                        break;
                    }
                }
                if (numValid) {
                    data.add(instance);
                }
            }
        }
    }
    private double predict(double[] instance) {
        double sum = threshold;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * instance[i];
        }
        return sum > 0 ? 1.0 : 0.0;
    }
    private void test() {
        int correct = 0;
        for (double[] instance : testData) {
            double predicted = predict(instance);
            double actual = instance[instance.length - 1];
            if (predicted == actual) {
                correct++;
            }
        }
        double accuracy = (double) correct / testData.size() * 100;
        System.out.println("Test accuracy: " + accuracy + "%");
    }
    private void test2() {
        int correct = 0;
        int total = 0;
        for (double[] instance : testData) {
            double predicted = predict(instance);
            String actualLabel = String.valueOf(instance[instance.length - 1]);
            double actual = Objects.equals(actualLabel, instance[instance.length - 1]) ? 1.0 : 0.0;
            if (predicted == actual) {
                correct++;
            }
            total++;
        }
        double accuracy = (double) correct / total * 100;
        System.out.println("Test accuracy for second data type: " + accuracy + "%");
    }
    public void setTestData(String testDataPath) throws IOException {
        if (isSecondDataType(testDataPath)) {
            System.out.println("Loading second data type");
            loadData2(testDataPath, testData);
        } else {
            System.out.println("Loading first data type");
            loadData(testDataPath, testData);
        }
        System.out.println("Data loaded successfully.");
    }
    private boolean isSecondDataType(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String firstLine = reader.readLine();
            System.out.println("First line of file: " + firstLine);
            if (firstLine != null) {
                String[] parts = firstLine.trim().split(",");
                if (parts.length > 3) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error detected!!!");
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to training data: ");
        String trainingDataPath = scanner.nextLine();
        System.out.print("Enter path to test data: ");
        String testDataPath = scanner.nextLine();
        double learningRate;
        while (true) {
            try {
                System.out.print("Enter learning rate: ");
                String rateInput = scanner.next();
                learningRate = Double.parseDouble(rateInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!!!");
            }
        }
        System.out.print("Enter number of epochs: ");
        int epochs = scanner.nextInt();
        Perceptron perceptron = new Perceptron(getNumbers(trainingDataPath), learningRate);
        perceptron.setTestData(testDataPath);
        if (perceptron.isSecondDataType(trainingDataPath)) {
            perceptron.train2(trainingDataPath, epochs);
        } else {
            perceptron.train(trainingDataPath, epochs);
        }
        while (true) {
            System.out.print("Enter new observation or type 'quit' to exit: ");
            String input = scanner.next();
            if (input.equals("quit")) {
                System.out.println("GOODBYE!");
                break;
            }
            String[] parts = input.trim().split(",");
            double[] observation = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                observation[i] = Double.parseDouble(parts[i]);
            }
            double prediction = perceptron.predict(observation);
            System.out.println("Predicted class: " + prediction);
        }
    }
    private static int getNumbers(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String[] attributes = reader.readLine().split(",");
        reader.close();
        return attributes.length - 1;
    }
}