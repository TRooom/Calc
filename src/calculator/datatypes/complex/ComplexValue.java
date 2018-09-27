package calculator.datatypes.complex;

import calculator.AbstractValue;
import calculator.DivisionByZeroException;

public class ComplexValue extends AbstractValue{
    private final double real;
    private final double imag;

    public ComplexValue(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public AbstractValue add(AbstractValue operand) {
        ComplexValue cOperand = ((ComplexValue) operand);

        return new ComplexValue(real + cOperand.real, imag + cOperand.imag);
    }

    public AbstractValue sub(AbstractValue operand) {
        ComplexValue cOperand = ((ComplexValue) operand);

        return this.add(new ComplexValue(-cOperand.real, -cOperand.imag));
    }

    public AbstractValue mul(AbstractValue operand) {
        ComplexValue cOperand = ((ComplexValue) operand);
        double newReal = this.real * cOperand.real - this.imag * cOperand.imag;
        double newImag = this.real * cOperand.imag + this.imag * cOperand.real;

        return new ComplexValue(newReal, newImag);
    }

    public AbstractValue div(AbstractValue operand) throws DivisionByZeroException {
        ComplexValue cOperand = ((ComplexValue) operand);

        if (cOperand.real == 0 && cOperand.imag == 0) {
            throw new DivisionByZeroException();
        }

        ComplexValue multiple = (ComplexValue) this.mul(((ComplexValue) operand).conjugated());

        double length = Math.sqrt(cOperand.real * cOperand.real + cOperand.imag * cOperand.imag);

        return new ComplexValue(multiple.real / length, multiple.imag / length);
    }

    public String toString() {
        if (this.real == 0) {
            return imagToString();
        }

        if (this.imag > 0) {
            return "".concat(Double.toString(this.real))
                    .concat("+")
                    .concat(imagToString());
        }

        return "".concat(Double.toString(this.real)).concat(imagToString());
    }

    private String imagToString() {
        if (this.imag == 0) {
            return "0";
        }
        if (this.imag == 1) {
            return "i";
        }
        if (this.imag == -1) {
            return "-i";
        }

        return Double.toString(this.imag).concat("i");
    }

    public ComplexValue conjugated() {
        return new ComplexValue(this.real, -this.imag);
    }
}