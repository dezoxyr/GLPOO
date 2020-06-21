package testMessage;
import utils.Message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class messageTest {

	@Test
	void testMessage() {
		Message a = new Message("Abs","Abs");
        assertEquals(a.getSender(),"Abs");
        assertEquals(a.getMessage(),"Abs");

        Message b = new Message("%_èAbs","%_èAbs");
        assertEquals(b.getSender(),"%_èAbs");
        assertEquals(b.getMessage(),"%_èAbs");
	}

	@Test
	void testGetSender() {
		Message b = new Message("%_èAbs","%_èAbs");
        assertEquals(b.getSender(),"%_èAbs");
        
		Message a = new Message("Abs","Abs");
        assertEquals(a.getSender(),"Abs");
	}

	@Test
	void testGetMessage() {
		Message b = new Message("%_èAbs","%_èAbs");
		assertEquals(b.getMessage(),"%_èAbs");
		
		Message a = new Message("Abs","Abs");
		assertEquals(a.getMessage(),"Abs");
	}
}
