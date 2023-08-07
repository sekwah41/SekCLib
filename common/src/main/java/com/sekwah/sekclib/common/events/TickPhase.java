package com.sekwah.sekclib.common.events;

/**
 * Some events will fire multiple times, this is to indicate which phase it has fired on e.g. entity updates
 */
public enum TickPhase {
    START,
    END
}
