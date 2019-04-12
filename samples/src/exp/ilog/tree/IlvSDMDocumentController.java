/*
 *  Copyright (C) 2001 by ILOG.
 *  All rights reserved.
 *
 * This source code is an addition to the ILOG JViews Reference Manual
 * delivered  with the JViews library.
 * It is supplied as an example to show you how to code with JViews.
 * Feel free to use, copy or modify it within the framework of your
 * JViews license agreement.
 */
/*
 * $Id: IlvSDMDocumentController.java,v 1.31 2001/09/26 14:15:19 gdigugli Exp $
 *
 */

package exp.ilog.tree;


import java.util.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import ilog.views.*;
import ilog.views.event.*;
import ilog.views.interactor.*;

import ilog.views.sdm.*;
import ilog.views.sdm.event.*;
import ilog.views.sdm.gui.event.*;
import ilog.views.sdm.gui.action.*;
import ilog.views.sdm.gui.util.*;

/**
 * <code>IlvSDMDocumentController</code> manages the documents, the document views, the application, and their actions. It allows you to register all the actions in use in the application and to share these actions between all the instances of GUI components which use Swing actions ({@link ilog.views.sdm.gui.IlvSDMToolBar} , {@link ilog.views.sdm.gui.IlvSDMMenuBar}).<br><br>
 *
 * Another role is to notify the documents and document views when they are active or nonactive: the controller can register {@link ilog.views.sdm.gui.event.IlvSDMActiveDocumentViewListener}s, which are notified of the active document view change events. <br>
 * It can also register <code>IlvSDMStylesheetChangedListener</code>s, which are notified of the style sheet changes. For example, the {@link ilog.views.sdm.gui.IlvSDMGrapherView} uses this event to update its style sheet when a new one is applied on another buffer.<br><br>
 *
 * For Multiple Document Interface (MDI) applications the buffer change events (active/inactive/closing) are managed by the <code>IlvSDMDesktopPane</code>, which transmits these events to the document controller. The <code>IlvSDMDocumentController</code> cannot be used without it. For SDI the document controller is useful to manage the state of the defined {@link ilog.views.sdm.gui.action.IlvSDMAction} but it does not manage the buffer life cycle (open/closing/closed).
 *
 * @see IlvSDMDocument
 * @see IlvSDMDocumentViewInterface
 * @see IlvSDMApplicationInterface
 */
public class IlvSDMDocumentController implements IlvSDMMessageListener, InteractorListener, SDMEngineStyleSheetListener
{
  private Vector activeDocumentViewListenerVector = new Vector(5);
  private Vector activeDocumentControlListenerVector = new Vector(5);
  private Vector stylesheetChangedListenerVector = new Vector(5);
  private Vector interactorListenerVector = new Vector(5);
  private IlvSDMDocument activeDocument;
  private Vector documents = new Vector(5);
  private IlvSDMApplicationInterface application;
  private HashMap defaultActions = new HashMap(40);
  private HashMap tableOfActionsCompatibility = new HashMap(40);
  private HashMap interactorActions = new HashMap(5);
  private Vector notifiedActions = new Vector(40);
  private Vector messagesListenerVector = new Vector(20);
  private String currentStyleSheet = null;
  private IlvSDMInteractorActionInterface defaultInteractorAction = null;

  /**
   * Creates an <code>IlvSDMDocumentController</code> attached to the specified application.
   * @param application The implementation of the <code>IlvSDMApplicationInterface</code> interface to which the controller is attached.
   */
  public IlvSDMDocumentController(IlvSDMApplicationInterface application)
  {
    this.application = application;
    initActions();
    setDefaultStylesheet(application.getDefaultStylesheet());
    addActiveDocumentViewListener(application);
  }

  /**
   * Returns an integer ID for a document.
   */
  protected int getNewDocumentId()
  {
    int max_id = 0;
    int current_id = 0;

    for(int i=0; i<this.documents.size(); i++) {
      current_id = ((IlvSDMDocument) this.documents.elementAt(i)).getDocumentId();
      if(max_id < current_id)
        max_id = current_id;
    }

    max_id++;

    return max_id;
  }

  /**
   * Initializes the actions.
   */
  private void initActions()
  {
/*
    try {
      Class appliClass    = Class.forName("ilog.views.sdm.gui.IlvSDMApplicationInterface");
      Class documentClass = Class.forName("ilog.views.sdm.gui.IlvSDMDocument");
      Class viewClass     = Class.forName("ilog.views.sdm.gui.IlvSDMDocumentViewInterface");
      Class grapherView   = Class.forName("ilog.views.sdm.gui.IlvSDMGrapherView");

      addAction(new AboutAction(this),                  appliClass);
      addAction(new AuthorsAction(this),                appliClass);
      addAction(new CascadeFramesAction(this),          appliClass);
      addAction(new ExitAction(this),                   appliClass);
      addAction(new HelpAction(this),                   appliClass);
      addAction(new NewDocumentAction(this),            appliClass);
      addAction(new OpenCSSAction(this),                appliClass);
      addAction(new OpenXMLAction(this),                appliClass);
      addAction(new TileFramesAction(this),             appliClass);
      addAction(new ToggleStatusBarAction(this),        appliClass);

      addAction(new SaveXMLAction(this),                documentClass);
      addAction(new GroupAction(this),                  documentClass);
      addAction(new UngroupAction(this),                documentClass);
      addAction(new ShowTableAction(this),              documentClass);
      addAction(new ShowTreeAction(this),               documentClass);
      addAction(new ReloadXMLAction(this),              documentClass);
      addAction(new ReloadCSSAction(this),              documentClass);
      addAction(new UndoAction(this),                   documentClass);
      addAction(new RedoAction(this),                   documentClass);
      addAction(new CutAction(this),                    documentClass);
      addAction(new CopyAction(this),                   documentClass);
      addAction(new AnimationRendererAction(this),      documentClass);
      addAction(new ColoringRendererAction(this),       documentClass);
      addAction(new DecorationRendererAction(this),     documentClass);
      addAction(new DeleteAction(this),                 documentClass);
      addAction(new DrillDownRendererAction(this),      documentClass);
      addAction(new DuplicateAction(this),              documentClass);
      addAction(new ExpandCollapseRendererAction(this), documentClass);
      addAction(new HalfZoomingRendererAction(this),    documentClass);
      addAction(new InfoBalloonRendererAction(this),    documentClass);
      addAction(new LegendRendererAction(this),         documentClass);
      addAction(new LinkLayoutRendererAction(this),     documentClass);
      addAction(new MapRendererAction(this),            documentClass);
      addAction(new PasteAction(this),                  documentClass);
      addAction(new SaveXMLAsAction(this),              documentClass);
      addAction(new StyleSheetRendererAction(this),     documentClass);
      addAction(new ToggleInspectorAction(this),        documentClass);

      addAction(new CloseAction(this),                  viewClass);
      addAction(new ExpandInteractorAction(this),       viewClass);
      addAction(new ShowOverviewAction(this),           viewClass);

      addAction(new ActivateAction(this),               grapherView);
      addAction(new AntialiasingAction(this),           grapherView);
      addAction(new BottomAlignAction(this),            grapherView);
      addAction(new EditLabelInteractorAction(this),    grapherView);
      addAction(new FitToContentsAction(this),          grapherView);
      addAction(new GridAction(this),                   grapherView);
      addAction(new HorizontalAlignAction(this),        grapherView);
      addAction(new HorizontalDistributeAction(this),   grapherView);
      addAction(new LeftAlignAction(this),              grapherView);
      addAction(new LinkLayoutPerformedAction(this),    grapherView);
      addAction(new LabelLayoutPerformedAction(this),   grapherView);
      addAction(new NewGrapherViewAction(this),         grapherView);
      addAction(new NodeLayoutPerformedAction(this),    grapherView);
      addAction(new OpaqueMoveAction(this),             grapherView);
      addAction(new PageSetupAction(this),              grapherView);
      addAction(new PanInteractorAction(this),          grapherView);
      addAction(new PrintAction(this),                  grapherView);
      addAction(new PrintInOnePageAction(this),         grapherView);
      addAction(new RightAlignAction(this),             grapherView);
      addAction(new SaveIVLAction(this),                grapherView);
      addAction(new SaveSVGAction(this),                grapherView);
      addAction(new SavePNGAction(this),                grapherView);
      addAction(new SelectInteractorAction(this),       grapherView);
      addAction(new TakeSnapshotAction(this),           grapherView);
      addAction(new TopAlignAction(this),               grapherView);
      addAction(new VerticalAlignAction(this),          grapherView);
      addAction(new VerticalDistributeAction(this),     grapherView);
      addAction(new ZoomBoxAction(this),                grapherView);
      addAction(new ZoomInAction(this),                 grapherView);
      addAction(new ZoomInitAction(this),               grapherView);
      addAction(new ZoomOutAction(this),                grapherView);


      // this two actions must be instanciate after the OpenXML & OpenCSS
      addAction(new HtmlBrowseDataAction(this),         grapherView);
      addAction(new HtmlBrowseStyleAction(this),        grapherView);
    }
    catch (Exception ex) {
      System.err.println(ex);
    }

*/

  }

  /**
   * Returns the current style sheet in use in the application.
   */
  public String getDefaultStylesheet()
  {
    return this.currentStyleSheet;
  }

  /**
   * Sets the current style sheet in use in the application.
   * @param newStyleSheet The new style sheet to use.
   */
  public void setDefaultStylesheet(String newStyleSheet)
  {
    this.currentStyleSheet = newStyleSheet;
  }

  /**
   * If the parameter is <code>true</code>, the default interactor is push automaticaly on the active document
   * view when all the interactors are poped from this view.
   * @param value The value of the <code>usingDefaultInteractor</code> flag.
   */
  public void setUsingDefaultInteractor(boolean value)
  {
    this.setUsingDefaultInteractor(value,null);
  }

  /**
   * If the parameter is <code>true</code>, the default interactor is push automaticaly on the active document
   * view when all the interactors are poped from this view.
   * @param value The value of the <code>usingDefaultInteractor</code> flag.
   * @param interactor Allows to specify the default interactor (case of multiple choice in the actions).
   */
  public void setUsingDefaultInteractor(boolean value, IlvSelectInteractor interactor)
  {
    if (value) {
      this.defaultInteractorAction = null;

      Set actionSet = this.interactorActions.entrySet();
      Iterator actionIteractor = actionSet.iterator();
      Map.Entry actionEntry;
      IlvSDMInteractorActionInterface actionValue;

      while(actionIteractor.hasNext())
      {
        actionEntry = (Map.Entry) actionIteractor.next();
        actionValue = (IlvSDMInteractorActionInterface) actionEntry.getValue();

        if (actionValue.isDefaultInteractor() && actionValue.getInteractors() != null && (actionValue.getInteractor() == interactor || interactor == null)) {
          this.defaultInteractorAction = actionValue;
        }
      }
    }
    else {
      this.defaultInteractorAction = null;
    }
  }

  /**
   * Returns <code>usingDefaultInteractor</code> flag.
   */
  public boolean isUsingDefaultInteractor()
  {
    return this.defaultInteractorAction != null;
  }

  /**
   * Returns <code>true</code> if all the referenced documents have an empty clipboard.
   */
  public boolean isClipBoardEmpty()
  {
    boolean isClipBoardEmpty = true;
    for(int i=0; i<this.documents.size(); i++) {
      isClipBoardEmpty = isClipBoardEmpty && ((IlvSDMDocument) this.documents.elementAt(i)).isClipBoardEmpty();
    }
    return isClipBoardEmpty;
  }

  /**
   * Return <code>true</code> if a referenced document is not saved.
   */
  public boolean areDocumentsChanged()
  {
    boolean areDocumentsChanged = true;
    if (this.documents.size() == 0) return false;

    for(int i=0; i<this.documents.size(); i++) {
      areDocumentsChanged = areDocumentsChanged && ((IlvSDMDocument) this.documents.elementAt(i)).isDocumentChanged();
    }
    return areDocumentsChanged;
  }

  /**
   * A debug method that prints on <code>System.err</code> all the referenced document and their document views.
   */
  public void printAllDocuments()
  {
    for(int i=0; i<this.documents.size(); i++) {
      ((IlvSDMDocument) this.documents.elementAt(i)).printAllViews();
    }
  }

  /**
   * Adds a new document to the controller.
   * @param document The document to add.
   */
  public void addDocument(IlvSDMDocument document)
  {
    this.documents.add(document);
    addStylesheetChangedListener(document);
    this.activeDocument = document;

  }

  /**
   * Removes a referenced document from the controller.
   * @param document The document to remove.
   */
  public void removeDocument(IlvSDMDocument document)
  {
    this.documents.remove(document);
    if (this.activeDocument == document)
      {
        this.activeDocument.getEngine().removeSDMEngineStyleSheetListener(this);
        this.activeDocument = null;
      }
    removeStylesheetChangedListener(document);
  }

  /**
   * Returns the number of referenced documents.
   */
  public int getDocumentCounter()
  {
    return this.documents.size();
  }

  /**
   * Returns a Swing action, which is applied to the active target object (a document or a view or an application).
   * If the target object is not available a default Swing action is returned (no callback and always disabled).
   * @param name The name of the Swing action.
   */
  public IlvSDMAction getAction(String name)
  {
    return getInternalAction(name);
  }

  /**
   * Returns a Swing action, which is applied to the active target object (a document or a view or an application).
   * If the target object is not available a default Swing action is returned (no callback and always disabled).
   * @param interactor The view interactor managed by the action.
   */
  public IlvSDMAction getInteractorAction(IlvManagerViewInteractor interactor)
  {
    return (IlvSDMAction) this.interactorActions.get(interactor);
  }

  /**
   * Returns a default Swing action, which is not functional and always disabled.
   * @param name The name of the Swing action.
   */
  private IlvSDMAction getInternalAction(String name)
  {
    return (IlvSDMAction) this.defaultActions.get(name);
  }

  /**
   * Debug method.
   */
  private void printInternalActions()
  {
    Set actionSet = this.defaultActions.entrySet();
    Iterator actionIterator = actionSet.iterator();
    Map.Entry actionEntry;
    AbstractAction actionValue;
    String actionName;

    System.err.println(" ------------------- <Controller Internal Actions> -------------------- ");

    while(actionIterator.hasNext())
      {
        actionEntry = (Map.Entry) actionIterator.next();
        actionName = (String) actionEntry.getKey();
        actionValue = (AbstractAction) actionEntry.getValue();

        System.err.print(actionValue.getClass().getName() + "\t\t\t");

        if(actionValue instanceof IlvSDMInteractorActionInterface) {
          System.err.println(actionName + "\t IlvSDMInteractorActionInterface");
        }
        else if (actionValue instanceof IlvSDMSelectableAction) {
          System.err.println(actionName + "\t IlvSDMSelectableAction");
        }
        else {
          System.err.println(actionName + "\t IlvSDMAction");
        }
      }

    System.err.println(" ------------------ </Controller Internal Actions> -------------------- ");
  }

  /**
   * Adds a Swing action to the controller.
   * @param action The new action to add.
   * @param supportedClass The class of components compatible with the action (the compatibility test is done with <code>isAssignableFrom</code> between this class and the active class of document|document view|application).
   */
  public void updateDefinedAction(IlvSDMAction action, Class supportedClass)
  {
    Object prevAction = defaultActions.get(action.getValue(AbstractAction.NAME));
    if (prevAction != null) {
      System.err.println("WARNING : Action '" + action.getValue(AbstractAction.NAME) + "' updated");
    }
    else {
      addAction(action, supportedClass);
    }

    this.defaultActions.put(action.getValue(AbstractAction.NAME), action);
    this.tableOfActionsCompatibility.put(action.getValue(AbstractAction.NAME), supportedClass);

    if (action instanceof IlvSDMInteractorActionInterface) {
      this.interactorActions.put( ((IlvSDMInteractorActionInterface) action).getInteractor(), action);
      if (((IlvSDMInteractorActionInterface) action).isDefaultInteractor()) {
        this.defaultInteractorAction = (IlvSDMInteractorActionInterface) action;
      }
    }

    if(action instanceof IlvSDMNotifiedAction) {
      int prevIndex = notifiedActions.indexOf(prevAction);
      if (prevIndex != -1)
        this.notifiedActions.setElementAt(action,prevIndex);
      else
        this.notifiedActions.add(action);
    }
  }

  /**
   * Adds a Swing action to the controller.
   * @param action The new action to add.
   * @param supportedClass The class of components compatible with the action (the compatibility test is done with <code>isAssignableFrom</code> between this class and the active class of document|document view|application).
   */
  public void addAction(IlvSDMAction action, Class supportedClass)
  {
    if (isActionAlreadyAdded(action)) {
      System.err.println("WARNING : Action '" + action.getValue(AbstractAction.NAME) + "'already added to the IlvSDMDocumentController.");
      return;
    }

    this.defaultActions.put(action.getValue(AbstractAction.NAME), action);
    this.tableOfActionsCompatibility.put(action.getValue(AbstractAction.NAME), supportedClass);

    if (action instanceof IlvSDMInteractorActionInterface) {
      this.interactorActions.put( ((IlvSDMInteractorActionInterface) action).getInteractor(), action);
      if (((IlvSDMInteractorActionInterface) action).isDefaultInteractor()) {
        this.defaultInteractorAction = (IlvSDMInteractorActionInterface) action;
      }
    }

    if(action instanceof IlvSDMNotifiedAction) {
      this.notifiedActions.add(action);
    }
  }

  private boolean isActionAlreadyAdded(IlvSDMAction action)
  {
    return getInternalAction((String) action.getValue(AbstractAction.SHORT_DESCRIPTION)) != null;
  }

  /**
   * Returns the active document of the controller.
   */
  public IlvSDMDocument getActiveDocument()
  {
    return this.activeDocument;
  }

  /**
   * Returns the active document view of the controller.
   */
  public IlvSDMDocumentViewInterface getActiveDocumentView()
  {
    if (this.activeDocument != null)
      return this.activeDocument.getActiveDocumentView();
    else
      return null;
  }

  /**
   * Returns the main document view of the controller.
   */
  public IlvSDMDocumentViewInterface getMainDocumentView()
  {
    if (this.activeDocument != null)
      return this.activeDocument.getMainDocumentView();
    else
      return null;
  }

  /**
   * Sets the active document view of the controller.
   * @param documentView The new active document view.
   */
  public void setActiveDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    this.activeDocument.setActiveDocumentView(documentView);
  }

  /**
   * Sets the main document view of the controller.
   * @param documentView The new main document view.
   */
  public void setMainDocumentView(IlvSDMDocumentViewInterface documentView)
  {
    this.activeDocument.setMainDocumentView(documentView);
  }

  /**
   * Sets the active document of the controller.
   * @param document The new active document.
   */
  public void setActiveDocument(IlvSDMDocument document)
  {
    if (this.activeDocument != null) {
      this.activeDocument.getEngine().removeSDMEngineStyleSheetListener(this);
    }
    this.activeDocument = document;
    this.activeDocument.getEngine().addSDMEngineStyleSheetListener(this);
  }

  /**
   * Returns the application of the controller.
   */
  public IlvSDMApplicationInterface getApplication()
  {
    return this.application;
  }

  /**
   * Adds an <code>IlvSDMMdiComponent</code> to be notified that the active document view has changed.
   * @param documentViewListener The document view listener to add.
   * @see #removeActiveDocumentViewListener
   * @see #fireActiveDocumentViewChanged
   */
  public void addActiveDocumentViewListener(IlvSDMActiveDocumentViewListener documentViewListener)
  {
    if (documentViewListener instanceof IlvSDMDocumentViewInterface)
      this.activeDocumentViewListenerVector.add(documentViewListener);
    else
      this.activeDocumentControlListenerVector.add(documentViewListener);

    if (documentViewListener instanceof InteractorListener)
      this.interactorListenerVector.add(documentViewListener);
  }

  /**
   * Removes a notified <code>IlvSDMMdiComponent</code>.
   * @param documentViewListener The document view listener to remove.
   * @see #removeActiveDocumentViewListener
   * @see #fireActiveDocumentViewChanged
   */
  public void removeActiveDocumentViewListener(IlvSDMActiveDocumentViewListener documentViewListener)
  {
    this.activeDocumentViewListenerVector.remove(documentViewListener);
    this.activeDocumentControlListenerVector.remove(documentViewListener);
  }

  /**
   * Notifies all the registered actions that they must update their state (enabled/disabled).
   */
  public void fireActionStateChangedEvent()
  {
    for(int i=0; i<this.notifiedActions.size(); i++) {
      ((IlvSDMNotifiedAction) this.notifiedActions.elementAt(i)).updateState(new ActionStateChangedEvent(this));
    }
  }

  /**
   * Notifies all the registered <code>IlvSDMActiveDocumentViewListener</code>s that the active document view has changed.
   * @param event The AWT event that can be the source of the update.
   */
  public void fireActiveDocumentViewChanged(AWTEvent event)
  {
    updateInteractorActions();
    fireActionStateChangedEvent();

    for(int i=0; i<this.activeDocumentViewListenerVector.size(); i++) {
      ((IlvSDMActiveDocumentViewListener) this.activeDocumentViewListenerVector.elementAt(i)).activeDocumentViewChanged(event);
    }
    for(int i=0; i<this.activeDocumentControlListenerVector.size(); i++) {
      ((IlvSDMActiveDocumentViewListener) this.activeDocumentControlListenerVector.elementAt(i)).activeDocumentViewChanged(event);
    }
  }

  private void updateInteractorActions()
  {
    Set actionSet = this.interactorActions.entrySet();
    Iterator actionIterator = actionSet.iterator();
    Map.Entry actionEntry;
    IlvSDMInteractorActionInterface actionValue;

    while(actionIterator.hasNext()) {
      actionEntry = (Map.Entry) actionIterator.next();
      actionValue = (IlvSDMInteractorActionInterface) actionEntry.getValue();
      if (actionValue.getInteractor().getManagerView() != null) {
        actionValue.getInteractor().getManagerView().removeAllInteractors();
      }
    }

    if (isUsingDefaultInteractor() &&
        getActiveDocumentView() != null &&
        defaultInteractorAction.getInteractor() instanceof IlvSelectInteractor) {

      IlvManagerViewInteractor[] interactors = defaultInteractorAction.getInteractors();
      if (interactors != null) {
        for (int i=0; i<interactors.length; i++) {
          getActiveDocumentView().getView().pushInteractor(interactors[i]);
        }
      }
    }
  }

  /**
   * Returns the <code>IlvSDMEngine</code> of the active document view.
   */
  public IlvSDMEngine getActiveDocumentViewEngine()
  {
    return getActiveDocument().getEngine();
  }

  /**
   * Adds an <code>IlvSDMMessageListener</code> that would be notified of the controller user messages.
   * @param listener The listener to add.
   * @see #removeMessageListener
   */
  public void addMessageListener(IlvSDMMessageListener listener)
  {
    this.messagesListenerVector.add(listener);
  }

  /**
   * Removes a notified <code>IlvSDMMessageListener</code>.
   * @param listener The listener to remove.
   * @see #addMessageListener
   */
  public void removeMessageListener(IlvSDMMessageListener listener)
  {
    this.messagesListenerVector.remove(listener);
  }

  /**
   * Implements <code>IlvSDMMessagesListener</code>.
   * This method is performed by the message sender.
   * @param event The message event that contains the text message.
   */
  public void messagePerformed(IlvSDMMessageEvent event)
  {
    for(int i=0; i<this.messagesListenerVector.size(); i++) {
      ((IlvSDMMessageListener) this.messagesListenerVector.elementAt(i)).messagePerformed(event);
    }
  }

  /**
   * Called when the interactor of a view changes.
   * @param event The modification event.
   */
  public void interactorChanged(InteractorChangedEvent event)
  {
    if (event.getNewValue() instanceof IlvSelectInteractorMoveSelection ||
        event.getNewValue() instanceof IlvSelectInteractorMultipleSelection) {
      return;
    }
    for(int i=0; i<this.interactorListenerVector.size(); i++) {
      ((InteractorListener) this.interactorListenerVector.elementAt(i)).interactorChanged(event);
    }
    if ( isUsingDefaultInteractor() && !(event.getOldValue() == defaultInteractorAction.getInteractor()) && (event.getNewValue() == null) ) {
      updateInteractorActions();
    }
    fireActionStateChangedEvent();
  }

  /**
   * Returns <code>true</code> if the action is enabled.
   * @param action The action.
   */
  public boolean isActionEnabled(IlvSDMAction action)
  {
    boolean isAssignableFromActiveApplication = false;
    boolean isAssignableFromActiveDocument = false;
    boolean isAssignableFromActiveDocumentView = false;
    Object actionName = action.getValue(AbstractAction.SHORT_DESCRIPTION);

    if (getApplication() != null) {
      isAssignableFromActiveApplication = ((Class) this.tableOfActionsCompatibility.get(actionName)).isAssignableFrom(getApplication().getClass());
    }
    if (getActiveDocument() != null) {
      isAssignableFromActiveDocument = ((Class) this.tableOfActionsCompatibility.get(actionName)).isAssignableFrom(getActiveDocument().getClass());

      if (getActiveDocument().getActiveDocumentView() != null) {
        isAssignableFromActiveDocumentView = ((Class) this.tableOfActionsCompatibility.get(actionName)).isAssignableFrom(getActiveDocument().getActiveDocumentView().getClass());
      }
    }

    return isAssignableFromActiveApplication || isAssignableFromActiveDocument || isAssignableFromActiveDocumentView;
  }

  /**
   * Invoked before the SDM engine starts loading a new style sheet file.
   * This method is called once for each entry of the array passed to
   * <code>IlvSDMEngine.setStyleSheets(java.lang.String[])</code>.
   * @param event The event.
   */
  public void styleSheetLoadingStarted(SDMEngineStyleSheetEvent event) {}

  /**
   * Invoked after the SDM engine has created a renderer specified in the style sheet being loaded.
   * @param event The event.
   */
  public void rendererCreated(SDMEngineRendererCreatedEvent event) {}

  /**
   * Invoked after the SDM engine has finished loading a new style sheet file.
   * This method is called once for each entry of the array passed to
   * <code>IlvSDMEngine.setStyleSheets(java.lang.String[])</code>.
   * @param event The event.
   */
  public void styleSheetLoadingDone(SDMEngineStyleSheetEvent event)
  {
    if (event.getStyleSheets()[0] != getDefaultStylesheet()) {
      setDefaultStylesheet(getActiveDocument().getEngine().getStyleSheets(0));
      fireDefaultStylesheetChanged();
    }
    fireActiveDocumentViewChanged(null);
  }

  /**
   * Fires an <code>IlvSDMStylesheetChangedEvent</code> to the registered <code>IlvSDMStylesheetChangedListener</code> classes.
   */
  private void fireDefaultStylesheetChanged()
  {
/*
    for(int i=0; i<this.stylesheetChangedListenerVector.size(); i++) {
      ((IlvSDMStylesheetChangedListener) this.stylesheetChangedListenerVector.elementAt(i)).stylesheetChanged(new IlvSDMStylesheetChangedEvent(this, getActiveDocument()));
    }
*/
  }

  /**
   * Registers an <code>IlvSDMStylesheetChangedListener</code> that will be notified of the default style sheet changes.
   * @param listener The listener to add.
   */
  public void addStylesheetChangedListener(IlvSDMStylesheetChangedListener listener)
  {
    this.stylesheetChangedListenerVector.add(listener);
  }

  /**
   * Unregisters a registered <code>IlvSDMStylesheetChangedListener</code>.
   * @param listener The listener to remove.
   */
  public void removeStylesheetChangedListener(IlvSDMStylesheetChangedListener listener)
  {
    this.stylesheetChangedListenerVector.remove(listener);
  }

  /**
   * Returns an array of all the {@link ilog.views.sdm.gui.IlvSDMDocument}s managed by the controller.
   */
  public IlvSDMDocument[] getAllDocuments()
  {
    Object[] documentsContains = documents.toArray();
    IlvSDMDocument[] docArray = new IlvSDMDocument[documentsContains.length];
    System.arraycopy(documentsContains, 0, docArray, 0, documentsContains.length);
    return docArray;
  }
}
