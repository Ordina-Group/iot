package be.ordina.iot.board;

import be.ordina.iot.exceptions.BoardFailureException;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Provides GPIO access.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RaspberryBoard {

    private GpioController gpio;

    private GpioPinDigitalInput button;
    private GpioPinDigitalOutput led;

    @PostConstruct
    private void attemptBoardInit() {
        try {
            gpio = GpioFactory.getInstance();
            configure();
        } catch (Throwable t) {
            System.out.println("Board init failed, are you running this code on a raspberry pi?");
            System.out.println("Cause: " + t.getMessage());
        }
    }

    private void configure() {
        //Set pin 2 to input mode, and the pin is pulled down (to GND) when the button is NOT pressed!
        button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, "BUTTON", PinPullResistance.PULL_DOWN);
        //Add a listener for input events.
        button.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("Button state changed: " + event.getPin() + " = " + event.getState());
                //TODO: Reporting button states is hard, nigh impossible via REST services (polling is possible but not recommended).
                //TODO: A better solution for buttons might be to have a websocket and send a message to all applicable clients when the button state changes.
            }
        });

        //Set pin 4 to output mode, and default low!
        led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "LED", PinState.LOW);
    }

    /**
     * Will toggle the state for the given duration.
     *
     * @param duration The duration the pulse should last (before reverting to the previous state).
     */
    public void ledPulse(final Long duration) throws Exception {
        if(led != null) {
            led.pulse(duration);
        } else {
            throw new BoardFailureException();
        }
    }
}