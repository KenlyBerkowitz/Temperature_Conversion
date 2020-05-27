public class Temperature {
    private float fahrenheit;
    private float celcius;

    //sets fahrenheit and calls conversion method to  
    //convert and set celcius
    public void setFahrenheit(float fTemp) {
        fahrenheit = fTemp;
        calculateCelcius();
    }

    //sets celcius and calls conversion method to  
    //convert and set fahrenheit
    public void setCelcius(float cTemp) {
        celcius = cTemp;
        calculateFahrenheit();
    }

    public float getFahrenheit() {
        return fahrenheit;
    }

    public float getCelcius() {
        return celcius;
    }

    //calculates celcius to fahrenheit
    private void calculateFahrenheit() {
        fahrenheit = (1.80f * celcius) + 32;
    }

    //calculates fahrenheit to celcius
    private void calculateCelcius() {
        celcius = (fahrenheit - 32 ) / 1.8f;
    }

}