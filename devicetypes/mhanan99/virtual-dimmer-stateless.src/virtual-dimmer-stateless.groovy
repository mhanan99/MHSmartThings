/**
 *  Universal Stateless Switch Dimmer (WAS a Virtual Contact/Switch)
 *  Can also be used as Momentary (as it is Stateless)
 *
 *  "Level" captured in Smartthings.  But changes to "Level" not triggered in Webcore  (not currently required anyway)
 *  A physical change in "Level" also changes the switch to "On"
 *
 *  Author: Mark Hanan
 *
 *  Date: 2019-10-02
 */
metadata {
        definition (name: "Virtual Dimmer Stateless", namespace: "mhanan99", author: "smartthings") {
        capability "Switch"
        capability "Refresh"
        capability "Switch Level"
        capability "Actuator"
		capability "Momentary"
        capability "Indicator"
    }

	// simulator metadata
	simulator {
	}

	// UI tile definitions
	tiles {
		standardTile("button", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: 'Off', action: "switch.on", icon: "st.Kids.kid10", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: 'On', action: "switch.off", icon: "st.Kids.kid10", backgroundColor: "#79b821", nextState: "off"
			state "off2", label: 'Off', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#bfbfbf", nextState: "on2"
			state "on2", label: 'On', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821", nextState: "off2"
		}
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
		}        
        controlTile("levelSliderControl", "device.level", "slider", height: 1, width: 2, inactiveLabel: false, backgroundColor:"#ffe71e") {
            state "level", action:"switch level.setLevel"
        }
        valueTile("lValue", "device.level", inactiveLabel: true, height:1, width:1, decoration: "flat") {
            state "levelValue", label:'${currentValue}%', unit:"", backgroundColor: "#53a7c0"
        }

		main(["button"])
		details(["button", "refresh","levelSliderControl","lValue"])
	}
}

def parse(String description) {
}

def on() {
	sendEvent(name: "switch", value: "on")
   	sendEvent(name: "contact", value: "resetting")
   	sendEvent(name: "switch", value: "on2")
   	log.info "Dimmer On"
}

def off() {
	sendEvent(name: "switch", value: "off")
	sendEvent(name: "contact", value: "resetting")
	sendEvent(name: "switch", value: "off2")
	log.info "Dimmer Off"
}

def setLevel(val){
    log.info "setLevel $val"
    
    if (val < 0) val = 0
    else if( val > 100) val = 100
    
    if(val == 0) off() else {
 	on()
 	sendEvent(name: "level", value: val)
    }
}

def refresh() {
    log.info "refresh"
}