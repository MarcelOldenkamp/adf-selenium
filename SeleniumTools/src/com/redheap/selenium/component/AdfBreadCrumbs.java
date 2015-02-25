package com.redheap.selenium.component;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class AdfBreadCrumbs extends AdfComponent {

    // no subids

    public AdfBreadCrumbs(WebDriver webDriver, String clientid) {
        super(webDriver, clientid);
    }

    @Override
    protected String getExpectedComponentType() {
        return "oracle.adf.RichBreadCrumbs";
    }

    public List<AdfCommandNavigationItem> getCrumbs() {
        List<ComponentReference> desc = getDescendantComponents();
        List<AdfCommandNavigationItem> retval = new ArrayList<AdfCommandNavigationItem>(desc.size());
        for (ComponentReference comp : desc) {
            if (AdfCommandNavigationItem.COMPONENT_TYPE.equals(comp.getComponentType())) {
                retval.add(AdfComponent.forClientId(getDriver(), comp.getClientid(), AdfCommandNavigationItem.class));
            }
        }
        return retval;
    }

}
