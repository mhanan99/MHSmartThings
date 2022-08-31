/**
 *  Universal Stateless Switch/Momentary
 *  Stateless Switch can be used as Momentary due to Stateless
 *
 *  Author: Mark Hanan
 *
 *  Date: 2019-02-06
 */
metadata {
	definition (name: "Stateless Switch/Momentary", namespace: "mhanan99", author: "Mark Hanan") {
		capability "Actuator"
		capability "Switch"
		capability "Momentary"
	}

	// simulator metadata
	simulator {
		
	}

	// UI tile definitions
	tiles {
		standardTile("button", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off2", label: 'Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#bfbfbf", nextState: "on2"
			state "on2", label: 'On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821", nextState: "off2"
			state "off", label: 'Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#bfbfbf"
			state "on", label: 'On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
		}
        standardTile("buttonOn", "device.switchOn", width: 1, height: 1) {
			state "on", label: 'On', action: "switch.on", icon: "st.switches.switch.on", backgroundColor: "#79b821"
		}
        standardTile("buttonOff", "device.switchOff", width: 1, height: 1) {
			state "off", label: 'Off', action: "switch.off", icon: "st.switches.switch.off", backgroundColor: "#bfbfbf"
		}
		main "button"
		details "button","buttonOn","buttonOff"
	}
}

def parse(description) {
}

def on() {
    sendEvent(name: "switch", value: "on")
    runin(1, sendEvent(name: "switch", value: "On"))
    
    
   
}

def off() {
    sendEvent(name: "switch", value: "off")
    runin(1, sendEvent(name: "switch", value: "Off"))
    
}    
    