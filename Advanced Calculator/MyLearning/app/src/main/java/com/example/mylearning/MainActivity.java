package com.example.mylearning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEquals, buttonClear, buttonDot;
    TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        buttonPlus=findViewById(R.id.buttonPlus);
        buttonMinus=findViewById(R.id.buttonMinus);
        buttonMultiply=findViewById(R.id.buttonMultiply);
        buttonDivide=findViewById(R.id.buttonDivide);
        buttonEquals=findViewById(R.id.buttonEquals);
        buttonClear=findViewById(R.id.buttonClear);
        buttonDot=findViewById(R.id.buttonDot);
        textView1 =findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+"9");
            }
        });
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+" + ");
            }
        });
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+" - ");
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+" * ");
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+" / ");
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setText("");
                textView2.setText("");
            }
        });
        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText(textView2.getText()+".");
            }
        });
        buttonClear.setOnClickListener(view -> {
            textView1.setText("");
            textView2.setText("");
        });

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = textView2.getText().toString().trim();
                if (input.isEmpty()) {
                    return; // Handle empty input case
                }

                try {
                    List<String> postfix = infixToPostfix(input);
                    double result = evaluatePostfix(postfix);
                    textView2.setText("=" + result);
                } catch (Exception e) {
                    textView2.setText("Error");
                }
            }
        });
    }
    private List<String> infixToPostfix(String infix) {
        List<String> postfix = new ArrayList<>();
        Stack<String> operators = new Stack<>();

        StringBuilder currentNumber = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                currentNumber.append(c);
            } else if (c == ' ') {
                continue; // Ignore spaces
            } else {
                if (currentNumber.length() > 0) {
                    postfix.add(currentNumber.toString());
                    currentNumber.setLength(0);
                }
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek().charAt(0))) {
                    postfix.add(operators.pop());
                }
                operators.push(String.valueOf(c));
            }
        }
        if (currentNumber.length() > 0) {
            postfix.add(currentNumber.toString());
        }
        while (!operators.isEmpty()) {
            postfix.add(operators.pop());
        }
        return postfix;
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b != 0) {
                            stack.push(a / b);
                        } else {
                            throw new ArithmeticException("Division by zero");
                        }
                        break;
                }
            }
        }
        return stack.pop();
    }
}