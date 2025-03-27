# Perceptron Classifier

## Overview
This project implements a simple perceptron classifier in Java. The perceptron is a type of artificial neural network used for binary classification tasks. It uses supervised learning with a linear decision boundary.

## Features
- Supports two different data formats: `iris-data` and `numeric-data`.
- Adjustable learning rate and number of epochs.
- Can train on user-specified datasets.
- Supports testing with separate datasets.
- Predicts classifications for new observations.
- Uses random weight initialization.

## Project Structure
```
PerceptronProject/
│-- src/
│   │-- iris-data (Dataset File)
│   │-- numeric-data (Dataset File)
│   │-- Perceptron.java
```

## How It Works
1. **Training**: The perceptron is trained on a dataset where each instance consists of feature values and a label (0 or 1).
2. **Testing**: After training, the model evaluates accuracy using a separate test dataset.
3. **Prediction**: The user can input new data to classify using the trained model.

## Installation & Usage
### Prerequisites
- Java Development Kit (JDK) installed (Java 8+)
- A text editor or an IDE (e.g., IntelliJ, Eclipse, or VS Code)

### Running the Program
1. **Compile the Code**:
   ```sh
   javac Perceptron.java
   ```
2. **Run the Program**:
   ```sh
   java Perceptron
   ```
3. **Follow the prompts**:
   - Enter the path to training data.
   - Enter the path to test data.
   - Provide a learning rate.
   - Specify the number of epochs.
   - The perceptron will train and test on the dataset.
   - Enter new observations for classification.
   - Type `quit` to exit.

### Example Input/Output
```
Enter path to training data: iris-data
Enter path to test data: numeric-data
Enter learning rate: 0.1
Enter number of epochs: 10
Epoch 1 completed
Epoch 2 completed
...
Test accuracy: 95.3%
Enter new observation or type 'quit' to exit: 5.1,3.5,1.4,0.2
Predicted class: 0.0
```

## Code Explanation
### Key Components
- **Weights & Threshold**: Adjusted during training to minimize classification errors.
- **Training**: Uses the perceptron learning rule to update weights.
- **Prediction**: Uses a linear combination of input features and weights.
- **Data Loading**: Reads datasets from CSV-like text files.
- **Epochs & Learning Rate**: Configurable training parameters.

### Core Methods
| Method | Description |
|--------|-------------|
| `train()` | Trains the perceptron using the dataset |
| `train2()` | Alternative training method for a second data format |
| `predict()` | Classifies new instances based on trained weights |
| `test()` | Evaluates model accuracy using test data |
| `loadData()` | Reads and processes training/test data |

## Dataset Format
### 1. `iris-data`
```
5.6,3.0,4.5,1.5,Iris-versicolor
6.3,2.8,5.1,1.5,Iris-virginica
...
```
Each row contains feature values followed by the class label (0 or 1).

### 2. `numeric-data`
```
1,3,0
4,5,0
60,5,1
...
```

## Notes
- The perceptron works best with **linearly separable** data.
- Ensure dataset files are correctly formatted before running.
- The learning rate should be set appropriately to ensure convergence.
- The number of epochs determines how many times the dataset is used for training.

## Future Improvements
- Support for multi-class classification.
- Implement activation functions for better performance.
- Add GUI for easier interaction.

## License
This project is open-source and can be used freely.

---

