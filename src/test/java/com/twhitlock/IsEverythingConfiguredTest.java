package com.twhitlock;

import kitchensink.controller.MemberRegistration;
import kitchensink.model.Member;
import kitchensink.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * User: twhitlock
 * Date: 6/4/12
 * Time: 8:38 AM
 * Test to ensure I have arquillian configured correctly
 */
@RunWith(Arquillian.class)
public class IsEverythingConfiguredTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Member.class, MemberRegistration.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                        // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml", "test-ds.xml");
    }

    @Inject
    MemberRegistration memberRegistration;

    @Inject
    Logger log;



    @Test
    public void testSuccess() {
        assertTrue("Everything is configured", true);
    }

}
