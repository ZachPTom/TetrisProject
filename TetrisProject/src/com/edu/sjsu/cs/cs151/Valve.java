package com.edu.sjsu.cs.cs151;

import com.edu.sjsu.cs.cs151.Views.Message;

/**
 * Interface that each valve in controller implements
 */
public interface Valve
{
    ValveResponse execute(Message message);
}
