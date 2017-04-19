package nth.meyn.cx.sysmac.converter.cx.symbolOLD;

public enum CxSystemVariables {
	P_GE, //Greater Than or Equals (GE) Flag  BOOL CF00
	P_NE, //Not Equals (NE) Flag  BOOL CF001
	P_LE, //Less Than or Equals (LE) Flag  BOOL CF002
	P_ER, //Instruction Execution Error (ER) Flag  BOOL CF003
	P_CY, //Carry (CY) Flag  BOOL CF004
	P_GT, //Greater Than (GT) Flag  BOOL CF005
	P_EQ, //Equals (EQ) Flag  BOOL CF006
	P_LT, //Less Than (LT) Flag  BOOL CF007
	P_N, //Negative (N) Flag  BOOL CF008
	P_OF, //Overflow (OF) Flag  BOOL CF009
	P_UF, //Underflow (UF) Flag  BOOL CF010
	P_AER, //Access Error Flag  BOOL CF011
	P_Off, //Always OFF Flag  BOOL CF114
	P_On, //Always ON Flag  BOOL CF113
	P_0_02s, //Clock Pulses 0.02 second clock pulse bit  BOOL CF103
	P_0_1s, //0.1 second clock pulse bit  BOOL CF100
	P_0_2s, //0.2 second clock pulse bit  BOOL CF101
	P_1mim, //1 minute clock pulse bit  BOOL CF104
	P_1s, //1.0 second clock pulse bit  BOOL CF102
	P_First_Cycle, //First Cycle Flag  BOOL A200.11
	P_Step, //Step Flag BOOL A200.12
	P_First_Cycle_Task, //First Task Execution Flag  BOOL A200.15
	P_Max_Cycle_Time, //Maximum Cycle Time  UDINT A262
	P_Cycle_Time_Value, //Present Scan Time  UDINT A264
	P_Cycle_Time_Error, //Cycle Time Error Flag  BOOL A401.08
	P_Low_Battery, //Low Battery Flag  BOOL A402.04
	P_IO_Verify_Error, //I/O VerIFication Error Flag  BOOL A402.09
	P_Output_Off_Bit //Output OFF Bit  BOOL A500.15
}
