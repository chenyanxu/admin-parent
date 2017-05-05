/**
 * GNU Public License
 * Copyright (C) 2014 Free Software Foundation, Inc. <http://fsf.org>
 * <p/>
 * This file is part of library EasyFSM.
 * <p/>
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This library can be redistributed
 * or used in case this license is copied as it is.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Author : Ankit
 * Report bugs to : hiiankit (at) gmail (dot) com
 **/
package com.kalix.admin.state.core.action;

/**
 * <h1>An abstract implementation of action for a Finite State Machine (fsm)</h1>
 *
 * <p>This implementation provides a framework of the action part of the fsm.<br/>
 * This Class is an abstract class and needs to be implemented to instantiate.<br/>
 * </p>
 *
 * @version 1.00
 * @author ANKIT
 */
public abstract class FSMAction {

    /**
     *
     * Abstract method; needs to be implemented<br/>
     *
     * @param curState <br/>
     * This value represents the Current State of the fsm. <br/>
     * @param message <br/>
     * This value specifies the Message for the fsm in Current state. <br/>
     * @param args <br/>
     * This value specifies the argument if any to be passed to the State Node.<br/>
     * @param nextState <br/>
     * This value specifies the State to be transitioned to; iff, Fsm transition
     * happens.
     * @return
     */
    public abstract boolean action(String curState, String message, String nextState, Object args);

    /**
     * Method is called after successful execution of the fsm transition.<br/>
     * Not mandatory to be implemented; can be over-ridden<br/>
     *
     * @param curState <br/>
     * This value represents the Current State of the fsm. <br/>
     * @param message <br/>
     * This value specifies the Message for the fsm in Current state. <br/>
     * @param args <br/>
     * This value specifies the argument if any to be passed to the State Node.<br/>
     * @param nextState <br/>
     * This value specifies the State to be transitioned to; iff, Fsm transition
     * happens.
     */
    public void afterTransition(String curState, String message, String nextState, Object args) {
        /*
         * Can be used by the calling function after transition has taken place
         */
    }

    /**
     * Method is called before action method is invoked.<br/>
     * Not mandatory to be implemented; can be over-ridden<br/>
     *
     * @param curState <br/>
     * This value represents the Current State of the fsm. <br/>
     * @param message <br/>
     * This value specifies the Message for the fsm in Current state. <br/>
     * @param args <br/>
     * This value specifies the argument if any to be passed to the State Node.<br/>
     * @param nextState <br/>
     * This value specifies the State to be transitioned to; iff, Fsm transition
     * happens.
     */
    public void entry(String curState, String message, String nextState, Object args) {
        /*
         * Can be used by the calling function after transition has taken place
         */
    }

    /**
     * Method is called after action method is invoked. This method is invoked<br/>
     * irrespective of transition status.<br/>
     * Not mandatory to be implemented; can be over-ridden<br/>
     *
     * @param curState <br/>
     * This value represents the Current State of the fsm. <br/>
     * @param message <br/>
     * This value specifies the Message for the fsm in Current state. <br/>
     * @param args <br/>
     * This value specifies the argument if any to be passed to the State Node.<br/>
     * @param nextState <br/>
     * This value specifies the State to be transitioned to; iff, Fsm transition
     * happens.
     */
    public void exit(String curState, String message, String nextState, Object args) {
        /*
         * Can be used by the calling function after transition has taken place
         */
    }

}
