package calculator.datatypes.complex;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;

public class ComplexValueParser implements AbstractValueParser {
    public AbstractValue parse(String value) throws ParseValueException {
        try {
            String[] values = value.split("[+]");

            if (values.length == 1) {
                if (values[0].endsWith("i")) {
                    String[] vals = values[0].split("-");
                    if (vals.length == 3) {
                        return new ComplexValue(-Double.parseDouble(vals[1]), -parseImag(vals[2]));
                    }
                    if (vals.length == 2) {
                        if (vals[0].equals("")) {
                            return new ComplexValue(0, -parseImag(vals[1]));
                        }

                        return new ComplexValue(Double.parseDouble(vals[0]), -parseImag(vals[1]));
                    }

                    return new ComplexValue(0, parseImag(values[0]));
                } else {
                    return new ComplexValue(Double.parseDouble(values[0]), 0);
                }
            } else {
                if (values.length == 2) {
                    return new ComplexValue(Double.parseDouble(values[0]), parseImag(values[1]));
                }

                throw new Error();
            }

        } catch (Exception e) {
            throw new ParseValueException();
        }
    }

    @Override
    public String getDatatypeName() {
        return "complex numbers";
    }

    private double parseImag(String imag) {
        if (imag.equals("i")) {
            return 1;
        }

        return Double.parseDouble(imag.replace("i", ""));
    }

    public String getDataTypeName() {
        return "Complex numbers";
    }
}