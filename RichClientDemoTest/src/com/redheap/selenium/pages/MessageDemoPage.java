package com.redheap.selenium.pages;

import com.redheap.selenium.component.AdfCommandButton;
import com.redheap.selenium.component.AdfInputText;
import com.redheap.selenium.component.AdfMessage;
import com.redheap.selenium.component.AdfPanelFormLayout;
import com.redheap.selenium.component.ComponentReference;
import com.redheap.selenium.page.Page;

import org.openqa.selenium.WebDriver;

public class MessageDemoPage extends Page {

    private final String message = "dmoTpl:message1";
    private final String editor = "dmoTpl:tagEditor:editor:pfl1";

    public MessageDemoPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getExpectedTitle() {
        return "message Demo";
    }

    public AdfMessage findMessage() {
        return findAdfComponent(message);
    }

    protected AdfInputText findMessageEditor() {
        return findFormItem("message");
    }

    protected AdfInputText findMessageTypeEditor() {
        return findFormItem("messageType");
    }

    private AdfInputText findFormItem(String label) {
        AdfPanelFormLayout form = findAdfComponent(editor);
        for (ComponentReference child : form.getChildComponents()) {
                if ("oracle.adf.RichInputText".equals(child.getComponentType())) {
                    AdfInputText it = child.findComponent(form.getDriver());
                    if (label.equals(it.getLabel())) {
                        return it;
                    }
                }
        }
        return null;
    }

    protected AdfCommandButton findEditorUpdateButton() {
        AdfPanelFormLayout form = findAdfComponent(editor);
        for (ComponentReference child : form.getChildComponents()) {
                if ("oracle.adf.RichCommandButton".equals(child.getComponentType())) {
                    AdfCommandButton b = child.findComponent(form.getDriver());
                    if ("Update".equals(b.getText())) {
                        return b;
                    }
                }
        }
        return null;

    }

    public void setMessage(String msg) {
        findMessageEditor().typeValue(msg);
        findEditorUpdateButton().click();
    }
    public void setMessageType(String type) {
        findMessageTypeEditor().typeValue(type);
        findEditorUpdateButton().click();
    }

}
