package de.marciland.windowhandler;

import static de.marciland.utilities.Constants.imagePath;

import de.marciland.profilehandler.Profile;
import de.marciland.recipehandler.Ingredient;
import de.marciland.recipehandler.Loader;
import de.marciland.utilities.Tools;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.RIGHT;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import static java.awt.Font.PLAIN;
import static java.awt.Font.SANS_SERIF;

import java.awt.GraphicsEnvironment;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Menu {

    private final double scale = 0.7;

    private final String title = "Essensplaner";
    private final String iconPath = imagePath + "icon.png";

    private JFrame mainFrame;

    private JDialog profilesInfoDialog, profilesEditDialog;
    private JDialog recipesAddDialog;
    private JDialog addIngredientDialog;
    private JDialog availableIngredientsDialog;

    private JLabel profileLabel;
    private JLabel profilesInfoHeightLabel, profilesInfoWeightLabel;
    private JLabel profilesInfoAgeLabel, profilesInfoKcalLabel, profilesInfoGoalLabel;
    private JLabel profilesEditHeightLabel, profilesEditWeightLabel;
    private JLabel profilesEditAgeLabel, profilesEditKcalLabel, profilesEditGoalLabel;
    private JLabel recipesAddNameLabel, recipesAddIngredientsLabel;
    private JLabel addIngredientLabel;
    private JLabel availableIngredientsLabel;

    private JTextField profilesEditHeightTextField, profilesEditWeightTextField;
    private JTextField profilesEditAgeTextField, profilesEditKcalTextField, profilesEditGoalTextField;
    private JTextField recipesAddNameTextField;
    private JTextField addIngredientTextField;

    private JButton profile1Button, profile2Button;
    private JButton recipesButton, planButton, profilesButton, shoppingButton;
    private JButton recipesInfoButton, recipesAddButton, addIngredientsButton;
    private JButton profilesInfoButton, profilesEditButton, profilesChangeButton;
    private JButton profilesInfoDialogOKButton;
    private JButton profilesEditDialogAcceptButton, profilesEditDialogCancelButton;
    private JButton recipesAddContinueButton, recipesAddCancelButton;
    private JButton recipesAddContinue2Button, recipesAddCancel2Button;
    private JButton recipesAddPlusButton, recipesAddMinusButton;
    private JButton addIngredientAddButton, addIngredientCancelButton;
    private JButton availableIngredientsChooseButton, availableIngredientsCancelButton;

    private JList<Object> recipesAddIngredientsList;
    private JList<Ingredient> availableIngredientsList;
    private JList<String> addIngredientList;

    private Profile currentProfile;

    private List<String> recipesAddList = new ArrayList<>();

    private JTable planTable, recipesTable;

    private Font profilesFont;
    private Font bigButtonFont, smallButtonFont;
    private Font dialogLabelFont, dialogTextFieldFont, dialogButtonFont, smallDialogButtonFont;

    private Dimension bigButtonSize, smallButtonSize, dialogSize;

    /*
     * returns true if the frame is displayed correctly.
     */
    public boolean init() {
        mainFrame = new JFrame(title);
        mainFrame.setLayout(null);
        DisplayMode display = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDisplayMode();
        mainFrame.setSize((int) (display.getWidth() * scale), (int) (display.getHeight() * scale));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setIconImage(new ImageIcon(iconPath).getImage());
        mainFrame.setVisible(true);
        if (!mainFrame.isDisplayable() || !mainFrame.isEnabled() || !mainFrame.isFocusable()
                || !mainFrame.isShowing()) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * loads all elements so that they can be accessed later.
     */
    public void loadAllElements() {
        long startTime = System.currentTimeMillis();
        loadUtilities();
        loadLoginElements();
        loadMenuElements();
        loadSubmenuElements();
        loadAllDialogElements();
        long endTime = System.currentTimeMillis();
        System.out.println("Loading time: " + (endTime - startTime) + "ms");
    }

    /*
     * creates login screen
     */
    public void create() {
        /*
         * add components to frame and repaint to make them visible.
         */
        mainFrame.add(profileLabel);
        mainFrame.add(profile1Button);
        mainFrame.add(profile2Button);
        mainFrame.repaint();
    }

    /*
     * loads utility elements so that they can be accessed.
     */
    private void loadUtilities() {
        /*
         * create instances of the components.
         */
        bigButtonSize = new Dimension(mainFrame.getWidth() / 4, mainFrame.getHeight() / 5);
        dialogSize = new Dimension(mainFrame.getWidth() / 3, mainFrame.getHeight() / 4);
        smallButtonSize = new Dimension(mainFrame.getWidth() / 4, mainFrame.getHeight() / 15);
        profilesFont = new Font(SANS_SERIF, PLAIN, 66);
        bigButtonFont = new Font(SANS_SERIF, PLAIN, 53);
        smallButtonFont = new Font(SANS_SERIF, PLAIN, 32);
        dialogLabelFont = new Font(SANS_SERIF, PLAIN, 19);
        dialogTextFieldFont = new Font(SANS_SERIF, PLAIN, 19);// TODO font size of dialog
        dialogButtonFont = new Font(SANS_SERIF, PLAIN, 25);
        smallDialogButtonFont = new Font(SANS_SERIF, PLAIN, 16);
    }

    /*
     * loads login elements so that they can be accessed later.
     */
    private void loadLoginElements() {
        /*
         * create instances of the components.
         */
        profileLabel = new JLabel("Wähle bitte ein Profil aus:");
        profile1Button = new JButton(Loader.loadProfileButton("profile1"));
        profile2Button = new JButton(Loader.loadProfileButton("profile2"));
        /*
         * set size and position of components.
         */
        profileLabel.setSize(mainFrame.getWidth() / 5 * 3, mainFrame.getHeight() / 5);
        profile1Button.setSize(mainFrame.getWidth() / 5, mainFrame.getHeight() / 5);
        profile2Button.setSize(mainFrame.getWidth() / 5, mainFrame.getHeight() / 5);
        profileLabel.setLocation(mainFrame.getWidth() / 5, mainFrame.getHeight() / 10);
        profile1Button.setLocation(mainFrame.getWidth() / 5, mainFrame.getHeight() / 2);
        profile2Button.setLocation(mainFrame.getWidth() / 5 * 3, mainFrame.getHeight() / 2);
        /*
         * set decorations of components.
         */
        profileLabel.setHorizontalAlignment(CENTER);
        profileLabel.setFont(profilesFont);
        profile1Button.setFont(profilesFont);
        profile2Button.setFont(profilesFont);
        profile1Button.setBorderPainted(false);
        profile2Button.setBorderPainted(false);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        profile1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * remove all components and load main menu with profile1.
                 */
                currentProfile = Loader.loadProfile("profile1", mainFrame);
                if (currentProfile != null) {
                    mainFrame.getContentPane().removeAll();
                    mainFrame.add(recipesButton);
                    mainFrame.add(planButton);
                    mainFrame.add(profilesButton);
                    mainFrame.add(shoppingButton);
                    mainFrame.repaint();
                }
            }
        });
        profile2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * remove all components and load main menu with profile2.
                 */
                currentProfile = Loader.loadProfile("profile2", mainFrame);
                if (currentProfile != null) {
                    mainFrame.getContentPane().removeAll();
                    mainFrame.add(recipesButton);
                    mainFrame.add(planButton);
                    mainFrame.add(profilesButton);
                    mainFrame.add(shoppingButton);
                    mainFrame.repaint();
                }
            }
        });
    }

    /*
     * loads menu elements so that they can be accessed later.
     */
    private void loadMenuElements() {
        /*
         * create instances of the components.
         */
        shoppingButton = new JButton("Einkaufsliste");
        recipesButton = new JButton("<html> Rezepte<br/>& Zutaten</html>");
        planButton = new JButton("Essensplan");
        profilesButton = new JButton("Profil");
        planTable = new JTable(); // TODO datamodel plan table
        recipesTable = new JTable(); // TODO datamodel rezept table
        /*
         * set size and position of components.
         */
        shoppingButton.setSize(bigButtonSize);
        recipesButton.setSize(bigButtonSize);
        planButton.setSize(bigButtonSize);
        profilesButton.setSize(bigButtonSize);
        planTable.setSize(mainFrame.getWidth(), mainFrame.getHeight() / 5 * 4);
        recipesTable.setSize(mainFrame.getWidth(), mainFrame.getHeight() / 5 * 4);
        shoppingButton.setLocation(mainFrame.getWidth() / 4, 0);
        recipesButton.setLocation(0, 0);
        planButton.setLocation(mainFrame.getWidth() / 4 * 2, 0);
        profilesButton.setLocation(mainFrame.getWidth() / 4 * 3, 0);
        planTable.setLocation(0, mainFrame.getHeight() / 5);
        recipesTable.setLocation(0, mainFrame.getHeight() / 5);
        /*
         * set decorations of components.
         */
        shoppingButton.setFont(bigButtonFont);
        recipesButton.setFont(bigButtonFont);
        planButton.setFont(bigButtonFont);
        profilesButton.setFont(bigButtonFont);
        shoppingButton.setBorderPainted(false);
        recipesButton.setBorderPainted(false);
        planButton.setBorderPainted(false);
        profilesButton.setBorderPainted(false);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        shoppingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO einkaufsliste
            }
        });
        recipesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * split recipes section into more options
                 */
                mainFrame.remove(recipesButton);
                mainFrame.add(recipesInfoButton);
                mainFrame.add(recipesAddButton);
                mainFrame.add(addIngredientsButton);
                mainFrame.repaint();
            }
        });
        planButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * shows the eating plan for the current week
                 */
                mainFrame.add(planTable);
                planButton.setEnabled(false);
                mainFrame.remove(recipesTable);
                recipesInfoButton.setEnabled(true);
                mainFrame.repaint();
            }
        });
        profilesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * split profiles section into more options
                 */
                mainFrame.remove(profilesButton);
                mainFrame.add(profilesInfoButton);
                mainFrame.add(profilesEditButton);
                mainFrame.add(profilesChangeButton);
                mainFrame.repaint();
            }
        });
    }

    /*
     * loads submenu elements so that they can be accessed later.
     */
    private void loadSubmenuElements() {
        /*
         * create instances of the components.
         */
        recipesInfoButton = new JButton("Rezepte anzeigen");
        recipesAddButton = new JButton("Rezept hinzufügen");
        addIngredientsButton = new JButton("Zutat hinzufügen");
        profilesInfoButton = new JButton("Profil anzeigen");
        profilesEditButton = new JButton("Profil bearbeiten");
        profilesChangeButton = new JButton("Profil wechseln");
        /*
         * set size and position of components.
         */
        recipesInfoButton.setSize(smallButtonSize);
        recipesAddButton.setSize(smallButtonSize);
        addIngredientsButton.setSize(smallButtonSize);
        profilesInfoButton.setSize(smallButtonSize);
        profilesEditButton.setSize(smallButtonSize);
        profilesChangeButton.setSize(smallButtonSize);
        recipesInfoButton.setLocation(0, 0);
        recipesAddButton.setLocation(0, mainFrame.getHeight() / 15);
        addIngredientsButton.setLocation(0, mainFrame.getHeight() / 15 * 2);
        profilesInfoButton.setLocation(mainFrame.getWidth() / 4 * 3, 0);
        profilesEditButton.setLocation(mainFrame.getWidth() / 4 * 3, mainFrame.getHeight() / 15);
        profilesChangeButton.setLocation(mainFrame.getWidth() / 4 * 3, mainFrame.getHeight() / 15 * 2);
        /*
         * set decorations of components.
         */
        recipesInfoButton.setFont(smallButtonFont);
        recipesAddButton.setFont(smallButtonFont);
        addIngredientsButton.setFont(smallButtonFont);
        profilesInfoButton.setFont(smallButtonFont);
        profilesEditButton.setFont(smallButtonFont);
        profilesChangeButton.setFont(smallButtonFont);
        recipesInfoButton.setBorderPainted(false);
        recipesAddButton.setBorderPainted(false);
        addIngredientsButton.setBorderPainted(false);
        profilesInfoButton.setBorderPainted(false);
        profilesEditButton.setBorderPainted(false);
        profilesChangeButton.setBorderPainted(false);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        recipesInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * shows all recipes
                 */
                mainFrame.add(recipesTable);
                recipesInfoButton.setEnabled(false);
                mainFrame.remove(planTable);
                planButton.setEnabled(true);
                mainFrame.remove(recipesInfoButton);
                mainFrame.remove(recipesAddButton);
                mainFrame.remove(addIngredientsButton);
                mainFrame.add(recipesButton);
                mainFrame.repaint();
            }
        });
        recipesAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddButton.setEnabled(false);
                recipesAddDialog.getContentPane().removeAll();
                recipesAddList.clear();
                recipesAddIngredientsList.setListData(recipesAddList.toArray());
                recipesAddDialog.add(recipesAddNameLabel);
                recipesAddDialog.add(recipesAddNameTextField);
                recipesAddDialog.add(recipesAddContinueButton);
                recipesAddDialog.add(recipesAddCancelButton);
                recipesAddDialog.setVisible(true);
            }
        });
        addIngredientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addIngredientsButton.setEnabled(false);
                addIngredientDialog.getContentPane().removeAll();
                addIngredientDialog.add(addIngredientLabel);
                addIngredientDialog.add(addIngredientTextField);
                Ingredient[] ing = Loader.loadAllIngredients();
                ArrayList<String> names = new ArrayList<>();
                for (Ingredient ingredient : ing) {
                    names.add(ingredient.getName());
                }
                if (!names.isEmpty() && names != null) {
                    String[] namesStrings = new String[names.size()];
                    for (int i = 0; i < names.size(); i++) {
                        namesStrings[i] = names.get(i);
                    }
                    addIngredientList.setListData(namesStrings);// TODO sort list of ingredients
                }
                addIngredientDialog.add(addIngredientList);
                addIngredientDialog.add(addIngredientAddButton);
                addIngredientDialog.add(addIngredientCancelButton);
                addIngredientDialog.setVisible(true);
            }
        });
        profilesInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilesInfoButton.setEnabled(false);
                profilesInfoDialog.setTitle("Profil anzeigen: " + Tools.encode(currentProfile.getName()));
                profilesInfoHeightLabel.setText("Größe: " + String.valueOf(currentProfile.getHeight()) + " cm");
                profilesInfoWeightLabel.setText("Gewicht: " + String.valueOf(currentProfile.getWeight()) + " kg");
                profilesInfoAgeLabel.setText("Alter: " + String.valueOf(currentProfile.getAge()) + " Jahre");
                profilesInfoKcalLabel.setText("Umsatz: " + String.valueOf(currentProfile.getKcal()) + " kcal");
                profilesInfoGoalLabel.setText("Ziel: " + Tools.encode(currentProfile.getGoal()));
                profilesInfoDialog.setVisible(true);
            }
        });
        profilesEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilesEditButton.setEnabled(false);
                profilesEditDialog.setTitle("Profil bearbeiten: " + Tools.encode(currentProfile.getName()));
                profilesEditHeightTextField.setText(String.valueOf(currentProfile.getHeight()));
                profilesEditWeightTextField.setText(String.valueOf(currentProfile.getWeight()));
                profilesEditAgeTextField.setText(String.valueOf(currentProfile.getAge()));
                profilesEditKcalTextField.setText(String.valueOf(currentProfile.getKcal()));
                profilesEditGoalTextField.setText(Tools.encode(currentProfile.getGoal()));
                profilesEditDialog.setVisible(true);
            }
        });
        profilesChangeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * logs out of current profile and returns to login screen
                 */
                planButton.setEnabled(true);
                recipesInfoButton.setEnabled(true);
                mainFrame.getContentPane().removeAll();
                currentProfile = null;
                profile1Button.setText(Loader.loadProfileButton("profile1"));
                profile2Button.setText(Loader.loadProfileButton("profile2"));
                create();
            }
        });
    }

    /*
     * loads all dialog elements so that they can be accessed later.
     */
    private void loadAllDialogElements() {
        loadProfilesInfoDialog();
        loadProfilesEditDialog();
        loadRecipesAddDialog();
        loadRecipesAddSubDialog();
        loadAvailableIngredientsDialog();
        loadAddIngredientsDialog();
    }

    /*
     * loads profile info dialog elements so that they can be accessed later.
     */
    private void loadProfilesInfoDialog() {
        /*
         * create instances of the components.
         */
        profilesInfoDialog = new JDialog(mainFrame, true);
        profilesInfoHeightLabel = new JLabel();
        profilesInfoWeightLabel = new JLabel();
        profilesInfoAgeLabel = new JLabel();
        profilesInfoKcalLabel = new JLabel();
        profilesInfoGoalLabel = new JLabel();
        profilesInfoDialogOKButton = new JButton("OK");
        /*
         * set size and position of components.
         */
        profilesInfoDialog.setSize(dialogSize);
        profilesInfoHeightLabel.setSize(profilesInfoDialog.getWidth() / 2, profilesInfoDialog.getHeight() / 4);
        profilesInfoWeightLabel.setSize(profilesInfoDialog.getWidth() / 2, profilesInfoDialog.getHeight() / 4);
        profilesInfoAgeLabel.setSize(profilesInfoDialog.getWidth() / 2, profilesInfoDialog.getHeight() / 4);
        profilesInfoKcalLabel.setSize(profilesInfoDialog.getWidth() / 2, profilesInfoDialog.getHeight() / 4);
        profilesInfoGoalLabel.setSize(profilesInfoDialog.getWidth(), profilesInfoDialog.getHeight() / 4);
        profilesInfoDialogOKButton.setSize(profilesInfoDialog.getWidth() / 4, profilesInfoDialog.getHeight() / 4);
        profilesInfoDialog.setLocationRelativeTo(mainFrame);
        profilesInfoHeightLabel.setLocation(0, 0);
        profilesInfoWeightLabel.setLocation(profilesInfoDialog.getWidth() / 2, 0);
        profilesInfoAgeLabel.setLocation(0, profilesInfoDialog.getHeight() / 4);
        profilesInfoKcalLabel.setLocation(profilesInfoDialog.getWidth() / 2, profilesInfoDialog.getHeight() / 4);
        profilesInfoGoalLabel.setLocation(0, profilesInfoDialog.getHeight() / 2);
        profilesInfoDialogOKButton.setLocation(profilesInfoDialog.getWidth() / 8 * 3,
                profilesInfoDialog.getHeight() / 4 * 3);
        /*
         * set decorations of components.
         */
        profilesInfoDialog.setLayout(null);
        profilesInfoDialog.setUndecorated(true);
        profilesInfoHeightLabel.setHorizontalAlignment(CENTER);
        profilesInfoWeightLabel.setHorizontalAlignment(CENTER);
        profilesInfoAgeLabel.setHorizontalAlignment(CENTER);
        profilesInfoKcalLabel.setHorizontalAlignment(CENTER);
        profilesInfoGoalLabel.setHorizontalAlignment(CENTER);
        profilesInfoHeightLabel.setVerticalAlignment(CENTER);
        profilesInfoWeightLabel.setVerticalAlignment(CENTER);
        profilesInfoAgeLabel.setVerticalAlignment(CENTER);
        profilesInfoKcalLabel.setVerticalAlignment(CENTER);
        profilesInfoGoalLabel.setVerticalAlignment(CENTER);
        profilesInfoHeightLabel.setFont(dialogLabelFont);
        profilesInfoWeightLabel.setFont(dialogLabelFont);
        profilesInfoAgeLabel.setFont(dialogLabelFont);
        profilesInfoKcalLabel.setFont(dialogLabelFont);
        profilesInfoGoalLabel.setFont(dialogLabelFont);
        profilesInfoDialogOKButton.setFont(dialogButtonFont);
        profilesInfoDialogOKButton.setBorderPainted(false);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        profilesInfoDialogOKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilesInfoButton.setEnabled(true);
                profilesInfoDialog.setVisible(false);
            }
        });
        /*
         * add components to dialog
         */
        profilesInfoDialog.add(profilesInfoHeightLabel);
        profilesInfoDialog.add(profilesInfoWeightLabel);
        profilesInfoDialog.add(profilesInfoAgeLabel);
        profilesInfoDialog.add(profilesInfoKcalLabel);
        profilesInfoDialog.add(profilesInfoGoalLabel);
        profilesInfoDialog.add(profilesInfoDialogOKButton);
    }

    /*
     * loads profile edit dialog elements so that they can be accessed later.
     */
    private void loadProfilesEditDialog() {
        /*
         * create instances of the components.
         */
        profilesEditDialog = new JDialog(mainFrame, true);
        profilesEditHeightLabel = new JLabel("Größe: ");
        profilesEditWeightLabel = new JLabel("Gewicht: ");
        profilesEditAgeLabel = new JLabel("Alter: ");
        profilesEditKcalLabel = new JLabel("Umsatz: ");
        profilesEditGoalLabel = new JLabel("Ziel: ");
        profilesEditHeightTextField = new JTextField();
        profilesEditWeightTextField = new JTextField();
        profilesEditAgeTextField = new JTextField();
        profilesEditKcalTextField = new JTextField();
        profilesEditGoalTextField = new JTextField();
        profilesEditDialogAcceptButton = new JButton("Bestätigen");
        profilesEditDialogCancelButton = new JButton("Abbrechen");
        /*
         * set size and position of components.
         */
        profilesEditDialog.setSize(dialogSize);
        profilesEditHeightLabel.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 4);
        profilesEditWeightLabel.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 4);
        profilesEditAgeLabel.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 4);
        profilesEditKcalLabel.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 4);
        profilesEditGoalLabel.setSize(profilesEditDialog.getWidth() / 2, profilesEditDialog.getHeight() / 4);
        profilesEditHeightTextField.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 8);
        profilesEditWeightTextField.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 8);
        profilesEditAgeTextField.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 8);
        profilesEditKcalTextField.setSize(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 8);
        profilesEditGoalTextField.setSize(profilesEditDialog.getWidth() / 2, profilesEditDialog.getHeight() / 8);
        profilesEditDialogAcceptButton.setSize(profilesEditDialog.getWidth() / 2, profilesEditDialog.getHeight() / 4);
        profilesEditDialogCancelButton.setSize(profilesEditDialog.getWidth() / 2, profilesEditDialog.getHeight() / 4);
        profilesEditDialog.setLocationRelativeTo(mainFrame);
        profilesEditHeightLabel.setLocation(0, 0);
        profilesEditWeightLabel.setLocation(profilesEditDialog.getWidth() / 2, 0);
        profilesEditAgeLabel.setLocation(0, profilesEditDialog.getHeight() / 4);
        profilesEditKcalLabel.setLocation(profilesEditDialog.getWidth() / 2, profilesEditDialog.getHeight() / 4);
        profilesEditGoalLabel.setLocation(0, profilesEditDialog.getHeight() / 2);
        profilesEditHeightTextField.setLocation(profilesEditDialog.getWidth() / 4, profilesEditDialog.getHeight() / 16);
        profilesEditWeightTextField.setLocation(profilesEditDialog.getWidth() / 4 * 3,
                profilesEditDialog.getHeight() / 16);
        profilesEditAgeTextField.setLocation(profilesEditDialog.getWidth() / 4,
                profilesEditDialog.getHeight() / 4 + profilesEditDialog.getHeight() / 16);
        profilesEditKcalTextField.setLocation(profilesEditDialog.getWidth() / 4 * 3,
                profilesEditDialog.getHeight() / 4 + profilesEditDialog.getHeight() / 16);
        profilesEditGoalTextField.setLocation(profilesEditDialog.getWidth() / 2,
                profilesEditDialog.getHeight() / 2 + profilesEditDialog.getHeight() / 16);
        profilesEditDialogAcceptButton.setLocation(0, profilesEditDialog.getHeight() / 4 * 3);
        profilesEditDialogCancelButton.setLocation(profilesEditDialog.getWidth() / 2,
                profilesEditDialog.getHeight() / 4 * 3);
        /*
         * set decorations of components.
         */
        profilesEditDialog.setLayout(null);
        profilesEditDialog.setUndecorated(true);
        profilesEditHeightLabel.setHorizontalAlignment(RIGHT);
        profilesEditWeightLabel.setHorizontalAlignment(RIGHT);
        profilesEditAgeLabel.setHorizontalAlignment(RIGHT);
        profilesEditKcalLabel.setHorizontalAlignment(RIGHT);
        profilesEditGoalLabel.setHorizontalAlignment(CENTER);
        profilesEditHeightLabel.setVerticalAlignment(CENTER);
        profilesEditWeightLabel.setVerticalAlignment(CENTER);
        profilesEditAgeLabel.setVerticalAlignment(CENTER);
        profilesEditKcalLabel.setVerticalAlignment(CENTER);
        profilesEditGoalLabel.setVerticalAlignment(CENTER);
        profilesEditHeightLabel.setFont(dialogLabelFont);
        profilesEditWeightLabel.setFont(dialogLabelFont);
        profilesEditAgeLabel.setFont(dialogLabelFont);
        profilesEditKcalLabel.setFont(dialogLabelFont);
        profilesEditGoalLabel.setFont(dialogLabelFont);
        profilesEditHeightTextField.setFont(dialogTextFieldFont);
        profilesEditWeightTextField.setFont(dialogTextFieldFont);
        profilesEditAgeTextField.setFont(dialogTextFieldFont);
        profilesEditKcalTextField.setFont(dialogTextFieldFont);
        profilesEditGoalTextField.setFont(dialogTextFieldFont);
        profilesEditDialogAcceptButton.setFont(dialogButtonFont);
        profilesEditDialogCancelButton.setFont(dialogButtonFont);
        profilesEditDialogAcceptButton.setBorderPainted(false);
        profilesEditDialogCancelButton.setBorderPainted(false);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        profilesEditDialogAcceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilesEditButton.setEnabled(true);
                // TODO override profile values, get from textbox input
                profilesEditDialog.setVisible(false);
            }
        });
        profilesEditDialogCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profilesEditButton.setEnabled(true);
                profilesEditDialog.setVisible(false);
            }
        });
        /*
         * add components to dialog
         */
        profilesEditDialog.add(profilesEditHeightLabel);
        profilesEditDialog.add(profilesEditWeightLabel);
        profilesEditDialog.add(profilesEditAgeLabel);
        profilesEditDialog.add(profilesEditKcalLabel);
        profilesEditDialog.add(profilesEditGoalLabel);
        profilesEditDialog.add(profilesEditHeightTextField);
        profilesEditDialog.add(profilesEditWeightTextField);
        profilesEditDialog.add(profilesEditAgeTextField);
        profilesEditDialog.add(profilesEditKcalTextField);
        profilesEditDialog.add(profilesEditGoalTextField);
        profilesEditDialog.add(profilesEditDialogAcceptButton);
        profilesEditDialog.add(profilesEditDialogCancelButton);
    }

    /*
     * loads recipes add dialog elements so that they can be accessed later.
     */
    private void loadRecipesAddDialog() {
        /*
         * create instances of the components.
         */
        recipesAddDialog = new JDialog(mainFrame, true);
        recipesAddNameLabel = new JLabel("Name des Rezepts: ");
        recipesAddNameTextField = new JTextField();
        recipesAddContinueButton = new JButton("Weiter");
        recipesAddCancelButton = new JButton("Abbrechen");
        /*
         * set size and position of components.
         */
        recipesAddDialog.setSize(dialogSize);
        recipesAddNameLabel.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 2);
        recipesAddNameTextField.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 4);
        recipesAddContinueButton.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 2);
        recipesAddCancelButton.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 2);
        recipesAddDialog.setLocationRelativeTo(mainFrame);
        recipesAddDialog.setLocation((int) recipesAddDialog.getLocation().getX() - recipesAddDialog.getWidth() / 2,
                (int) recipesAddDialog.getLocation().getY());
        recipesAddNameLabel.setLocation(0, 0);
        recipesAddNameTextField.setLocation(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 8);
        recipesAddContinueButton.setLocation(0, recipesAddDialog.getHeight() / 2);
        recipesAddCancelButton.setLocation(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 2);
        /*
         * set decorations of components.
         */
        recipesAddDialog.setLayout(null);
        recipesAddDialog.setUndecorated(true);
        recipesAddNameLabel.setHorizontalAlignment(RIGHT);
        recipesAddContinueButton.setBorderPainted(false);
        recipesAddCancelButton.setBorderPainted(false);
        recipesAddNameLabel.setFont(dialogLabelFont);
        recipesAddNameTextField.setFont(dialogTextFieldFont);
        recipesAddContinueButton.setFont(dialogButtonFont);
        recipesAddCancelButton.setFont(dialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        recipesAddContinueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO check existing
                boolean recipeExists = false;
                if (recipeExists) {
                    JOptionPane.showMessageDialog(recipesAddDialog, "Rezept existiert bereits!", "Fehler!",
                            JOptionPane.WARNING_MESSAGE);
                }
                // TODO check similar
                boolean recipeCouldExist = false;
                if (recipeCouldExist) {
                    // TODO show error: "meintest du:?"
                }
                if (!recipeExists && !recipeCouldExist) {
                    recipesAddDialog.getContentPane().removeAll();
                    recipesAddDialog.add(recipesAddIngredientsLabel);
                    recipesAddDialog.add(recipesAddIngredientsList);
                    recipesAddDialog.add(recipesAddPlusButton);
                    recipesAddDialog.add(recipesAddMinusButton);
                    recipesAddDialog.add(recipesAddContinue2Button);
                    recipesAddDialog.add(recipesAddCancel2Button);
                    recipesAddDialog.repaint();
                }
            }
        });
        recipesAddCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddButton.setEnabled(true);
                recipesAddNameTextField.setText("");
                recipesAddDialog.setVisible(false);
            }
        });
    }

    /*
     * loads recipes add sub dialog elements so that they can be accessed later.
     */
    private void loadRecipesAddSubDialog() {
        /*
         * create instances of the components.
         */
        recipesAddIngredientsLabel = new JLabel("Zutaten auswählen:");
        recipesAddIngredientsList = new JList<>();
        recipesAddContinue2Button = new JButton("Weiter");
        recipesAddPlusButton = new JButton("Hinzufügen");
        recipesAddMinusButton = new JButton("Entfernen");
        recipesAddCancel2Button = new JButton("Abbrechen");
        /*
         * set size and position of components.
         */
        recipesAddIngredientsLabel.setSize(recipesAddDialog.getWidth(), recipesAddDialog.getHeight() / 4);
        recipesAddIngredientsList.setSize(recipesAddDialog.getWidth(), recipesAddDialog.getHeight() / 2);
        recipesAddContinue2Button.setSize(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 4);
        recipesAddPlusButton.setSize(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 4);
        recipesAddMinusButton.setSize(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 4);
        recipesAddCancel2Button.setSize(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 4);
        recipesAddIngredientsLabel.setLocation(0, 0);
        recipesAddIngredientsList.setLocation(0, recipesAddDialog.getHeight() / 4);
        recipesAddContinue2Button.setLocation(0, recipesAddDialog.getHeight() / 4 * 3);
        recipesAddPlusButton.setLocation(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 4 * 3);
        recipesAddMinusButton.setLocation(recipesAddDialog.getWidth() / 4 * 2, recipesAddDialog.getHeight() / 4 * 3);
        recipesAddCancel2Button.setLocation(recipesAddDialog.getWidth() / 4 * 3, recipesAddDialog.getHeight() / 4 * 3);
        /*
         * set decorations of components.
         */
        recipesAddIngredientsLabel.setHorizontalAlignment(CENTER);
        recipesAddContinue2Button.setBorderPainted(false);
        recipesAddPlusButton.setBorderPainted(false);
        recipesAddMinusButton.setBorderPainted(false);
        recipesAddCancel2Button.setBorderPainted(false);
        recipesAddIngredientsLabel.setFont(dialogLabelFont);
        recipesAddContinue2Button.setFont(smallDialogButtonFont);
        recipesAddPlusButton.setFont(smallDialogButtonFont);
        recipesAddMinusButton.setFont(smallDialogButtonFont);
        recipesAddCancel2Button.setFont(smallDialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        recipesAddContinue2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO recipe description
                // validate data
                if (!Tools.validateIngredientsList(recipesAddIngredientsList.getModel())) {
                    // throw error
                } else {
                    recipesAddDialog.getContentPane().removeAll();
                    // add next dialog elements
                }
            }
        });
        recipesAddPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddPlusButton.setEnabled(false);
                availableIngredientsDialog.getContentPane().removeAll();
                availableIngredientsDialog.add(availableIngredientsLabel);
                availableIngredientsDialog.add(availableIngredientsList);
                availableIngredientsList.setListData(Loader.loadAllIngredients());
                availableIngredientsDialog.add(availableIngredientsChooseButton);
                availableIngredientsDialog.add(availableIngredientsCancelButton);
                availableIngredientsDialog.setVisible(true);
            }
        });
        recipesAddMinusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (recipesAddIngredientsList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(availableIngredientsDialog, "Nichts ausgewählt!", "Fehler!",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                recipesAddList.remove(recipesAddIngredientsList.getSelectedValue());
                recipesAddIngredientsList.setListData(recipesAddList.toArray());
                recipesAddDialog.repaint();
            }
        });
        recipesAddCancel2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddButton.setEnabled(true);
                recipesAddNameTextField.setText("");
                recipesAddDialog.setVisible(false);
            }
        });
    }

    /*
     * loads available ingredients dialog elements so that they can be accessed
     * later.
     */
    private void loadAvailableIngredientsDialog() {
        /*
         * create instances of the components.
         */
        availableIngredientsDialog = new JDialog(recipesAddDialog, true);
        availableIngredientsLabel = new JLabel("Verfügbare Zutaten");
        availableIngredientsList = new JList<>();
        availableIngredientsChooseButton = new JButton("Auswählen");
        availableIngredientsCancelButton = new JButton("Fertig");
        /*
         * set size and position of components.
         */
        availableIngredientsDialog.setSize(dialogSize);
        availableIngredientsLabel.setSize(availableIngredientsDialog.getWidth(),
                availableIngredientsDialog.getHeight() / 4);
        availableIngredientsList.setSize(availableIngredientsDialog.getWidth(),
                availableIngredientsDialog.getHeight() / 2);
        availableIngredientsChooseButton.setSize(availableIngredientsDialog.getWidth() / 2,
                availableIngredientsDialog.getHeight() / 4);
        availableIngredientsCancelButton.setSize(availableIngredientsDialog.getWidth() / 2,
                availableIngredientsDialog.getHeight() / 4);
        availableIngredientsDialog.setLocationRelativeTo(mainFrame);
        availableIngredientsDialog.setLocation(
                (int) availableIngredientsDialog.getLocation().getX() + availableIngredientsDialog.getWidth() / 2,
                (int) availableIngredientsDialog.getLocation().getY());
        availableIngredientsLabel.setLocation(0, 0);
        availableIngredientsList.setLocation(0, availableIngredientsDialog.getHeight() / 4);
        availableIngredientsChooseButton.setLocation(0, availableIngredientsDialog.getHeight() / 4 * 3);
        availableIngredientsCancelButton.setLocation(availableIngredientsDialog.getWidth() / 2,
                availableIngredientsDialog.getHeight() / 4 * 3);
        /*
         * set decorations of components.
         */
        availableIngredientsDialog.setLayout(null);
        availableIngredientsDialog.setUndecorated(true);
        availableIngredientsLabel.setHorizontalAlignment(CENTER);
        availableIngredientsChooseButton.setBorderPainted(false);
        availableIngredientsCancelButton.setBorderPainted(false);
        availableIngredientsLabel.setFont(dialogLabelFont);
        availableIngredientsChooseButton.setFont(smallDialogButtonFont);
        availableIngredientsCancelButton.setFont(smallDialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        availableIngredientsChooseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (availableIngredientsList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(availableIngredientsDialog, "Nichts ausgewählt!", "Fehler!",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                for (String string : recipesAddList) {
                    if (string.contains(availableIngredientsList.getSelectedValue().getName())) {
                        JOptionPane.showMessageDialog(recipesAddDialog, "Zutat befindet sich schon im Rezept!",
                                "Fehler!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                String input;
                getInput: switch (availableIngredientsList.getSelectedValue().getType()) {
                    case 1:
                        while (true) {
                            input = JOptionPane.showInputDialog(availableIngredientsDialog, "Wieviel von "
                                    + availableIngredientsList.getSelectedValue().getName()
                                    + " möchtest du hinzufügen?",
                                    "Bitte Menge in gramm angeben!", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break getInput;
                            }
                            if (!Tools.checkDouble(input)) {
                                JOptionPane.showMessageDialog(availableIngredientsDialog, "Ungültige Eingabe!",
                                        "Fehler!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                recipesAddList
                                        .add(input + "g " + availableIngredientsList.getSelectedValue().getName());
                                break getInput;
                            }
                        }
                    case 2:
                        while (true) {
                            input = JOptionPane.showInputDialog(availableIngredientsDialog, "Wieviel von "
                                    + availableIngredientsList.getSelectedValue().getName()
                                    + " möchtest du hinzufügen?",
                                    "Bitte Menge in ml angeben!", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break getInput;
                            }
                            if (!Tools.checkDouble(input)) {
                                JOptionPane.showMessageDialog(availableIngredientsDialog, "Ungültige Eingabe!",
                                        "Fehler!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                recipesAddList
                                        .add(input + "ml " + availableIngredientsList.getSelectedValue().getName());
                                break getInput;
                            }
                        }
                    case 3:
                        while (true) {
                            input = JOptionPane.showInputDialog(availableIngredientsDialog, "Wieviel von "
                                    + availableIngredientsList.getSelectedValue().getName()
                                    + " möchtest du hinzufügen?",
                                    "Bitte Menge in Stückzahl angeben!", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break getInput;
                            }
                            if (!Tools.checkDouble(input)) {
                                JOptionPane.showMessageDialog(availableIngredientsDialog, "Ungültige Eingabe!",
                                        "Fehler!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                recipesAddList
                                        .add(input + "stk. " + availableIngredientsList.getSelectedValue().getName());
                                break getInput;
                            }
                        }
                }
                recipesAddIngredientsList.setListData(recipesAddList.toArray());
                recipesAddDialog.repaint();
            }
        });
        availableIngredientsCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddPlusButton.setEnabled(true);
                availableIngredientsDialog.setVisible(false);
            }
        });
    }

    /*
     * loads available ingredients sub dialog elements so that they can be accessed
     * later.
     */
    private void loadAddIngredientsDialog() {
        /*
         * create instances of the components.
         */
        addIngredientDialog = new JDialog(mainFrame, true);
        addIngredientLabel = new JLabel("Name:");
        addIngredientTextField = new JTextField();
        addIngredientList = new JList<>();
        addIngredientAddButton = new JButton("Hinzufügen");
        addIngredientCancelButton = new JButton("Abbrechen");
        /*
         * set size and position of components.
         */
        addIngredientDialog.setSize(dialogSize);
        addIngredientLabel.setSize(addIngredientDialog.getWidth() / 2, addIngredientDialog.getHeight() / 2);
        addIngredientTextField.setSize(addIngredientDialog.getWidth() / 2, addIngredientDialog.getHeight() / 6);
        addIngredientList.setSize(addIngredientDialog.getWidth() / 2, addIngredientDialog.getHeight() / 3 * 2);
        addIngredientAddButton.setSize(addIngredientDialog.getWidth() / 2, addIngredientDialog.getHeight() / 3);
        addIngredientCancelButton.setSize(addIngredientDialog.getWidth() / 2, addIngredientDialog.getHeight() / 3);
        addIngredientDialog.setLocationRelativeTo(mainFrame);
        addIngredientLabel.setLocation(0, 0);
        addIngredientTextField.setLocation(0,
                addIngredientDialog.getHeight() / 3 + addIngredientDialog.getHeight() / 12);
        addIngredientList.setLocation(addIngredientDialog.getWidth() / 2, 0);
        addIngredientAddButton.setLocation(0, addIngredientDialog.getHeight() / 3 * 2);
        addIngredientCancelButton.setLocation(addIngredientDialog.getWidth() / 2,
                addIngredientDialog.getHeight() / 3 * 2);
        /*
         * set decorations of components.
         */
        addIngredientDialog.setLayout(null);
        addIngredientDialog.setUndecorated(true);
        addIngredientAddButton.setBorderPainted(false);
        addIngredientCancelButton.setBorderPainted(false);
        addIngredientLabel.setHorizontalAlignment(CENTER);
        addIngredientList.setFont(dialogTextFieldFont);
        addIngredientLabel.setFont(dialogLabelFont);
        addIngredientTextField.setFont(dialogTextFieldFont);
        addIngredientAddButton.setFont(dialogButtonFont);
        addIngredientCancelButton.setFont(dialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        // TODO actionlistener
        // addIngredientAddButton.addActionListener(l);
        // addIngredientCancelButton.addActionListener(l);
    }

}
