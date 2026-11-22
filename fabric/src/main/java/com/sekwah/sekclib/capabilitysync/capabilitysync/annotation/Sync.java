package com.sekwah.sekclib.capabilitysync.capabilitysync.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Sync {
    int minTicks() default 3;

    /**
     * Should this be synced with other players
     * TODO check if the player is spectating another player and send the data. This will sync UI while in spectator mode.
     *
     * @return
     */
    boolean syncGlobally() default false;
}
