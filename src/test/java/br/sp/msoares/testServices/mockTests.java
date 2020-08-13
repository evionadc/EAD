package br.sp.msoares.testServices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.sp.msoares.services.mockServices;

public class mockTests {

    @Mock
    mockServices mockServices;

    @Spy
    mockServices spyServices;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void deveRetornarumaSoma(){
        Mockito.doReturn(3).when(mockServices).somar(Mockito.anyInt(), Mockito.anyInt());
        int ret = mockServices.somar(1, 5);
        System.out.println(ret);
        Assert.assertEquals(3, ret);

    }
    @Test    
    public void deveRetornarumboleano(){
        Mockito.doReturn(true).when(mockServices).boleano(true, false);
        boolean retmock = mockServices.boleano(true,false);

        
        
        Mockito.doReturn(true).when(spyServices).boleano(true, true);
        boolean spymock = spyServices.boleano(true,true);
        System.out.println(retmock);
        System.out.println(spymock);
        Assert.assertTrue(spymock);
        Assert.assertTrue(retmock);
    }  
    
    @Test
    public void deveRetornarumaString(){
        Mockito.doReturn("Olá").when(mockServices).stringer(Mockito.anyString());
        String ret = mockServices.stringer("Olá");
        Assert.assertNotNull(ret);
    }
    
}