package com.amdocs.backend;

import java.time.LocalDateTime;

public class SLAAnalyzer 
{
    /**
    * Method will receive a particular problem opening date and amount of working hours (business hours) it should be solved and return the maximum date and time it should be solved.
    * It should be considered:
    * 	- Business hours are from 8AM to 5PM excluding weekends and holidays
    *  - Logic should consider only month of August 2019, Sao Carlos location
    *  - Method signature cannot be changed
    *  
    * @param iOpeningDateTime - Problem opening date
    * @param iSLA - Quantity of hours to solve the problem
    * @return Maximum date and time that problem should be solved
    */
    
    public LocalDateTime calculateSLA(LocalDateTime iOpeningDateTime, Integer iSLA)
    {
    	// your logic here
        return iOpeningDateTime;
    }
    
      
}
