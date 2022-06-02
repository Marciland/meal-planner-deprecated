package de.marciland.windowhandler;

import static de.marciland.utilities.Constants.imagePath;

import de.marciland.ingredienthandler.Ingredient;
import de.marciland.ingredienthandler.IngredientLoader;
import de.marciland.profilehandler.Profile;
import de.marciland.profilehandler.ProfileLoader;
import de.marciland.recipehandler.RecipeLoader;
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
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import java.util.ArrayList;

public class Menu {

    private final double scale = 0.7;

    private final String title = "Essensplaner";
    private final String iconPath = imagePath + "icon.png";

    private JFrame mainFrame;

    private JDialog profilesInfoDialog, profilesEditDialog;
    private JDialog recipesAddDialog;
    private JDialog ingredientAddDialog;
    private JDialog availableIngredientsDialog;

    private JLabel profileLabel;
    private JLabel profilesInfoHeightLabel, profilesInfoWeightLabel;
    private JLabel profilesInfoAgeLabel, profilesInfoKcalLabel, profilesInfoGoalLabel;
    private JLabel profilesEditHeightLabel, profilesEditWeightLabel;
    private JLabel profilesEditAgeLabel, profilesEditKcalLabel, profilesEditGoalLabel;
    private JLabel recipesAddNameLabel, recipesAddSimilarLabel, recipesAddIngredientsLabel;
    private JLabel recipesDescriptionLabel;
    private JLabel ingredientAddLabel, ingredientAddSimilarLabel;
    private JLabel availableIngredientsLabel;

    private JTextField profilesEditHeightTextField, profilesEditWeightTextField;
    private JTextField profilesEditAgeTextField, profilesEditKcalTextField, profilesEditGoalTextField;
    private JTextField recipesAddNameTextField, recipesDescriptionTextField;
    private JTextField ingredientAddTextField;

    private JButton profile1Button, profile2Button;
    private JButton recipesButton, planButton, profilesButton, shoppingButton;
    private JButton recipesInfoButton, recipesAddButton, ingredientsAddButton;
    private JButton profilesInfoButton, profilesEditButton, profilesChangeButton;
    private JButton profilesInfoDialogOKButton;
    private JButton profilesEditDialogAcceptButton, profilesEditDialogCancelButton;
    private JButton recipesAddContinueButton, recipesAddCancelButton;
    private JButton recipesAddContinue2Button, recipesAddCancel2Button;
    private JButton recipesAddYesButton, recipesAddNoButton;
    private JButton recipesAddPlusButton, recipesAddMinusButton;
    private JButton recipesDescriptionFinishButton, recipesDescriptionCancelButton;
    private JButton ingredientAddButton, ingredientAddCancelButton, ingredientAddYesButton, ingredientAddNoButton;
    private JButton availableIngredientsChooseButton, availableIngredientsCancelButton;

    private JList<Ingredient> availableIngredientsList;
    private JList<String> recipesAddExistingList, recipesAddSimilarList, recipesAddIngredientsList;
    private JList<String> ingredientAddList, ingredientAddSimilarList;

    private Profile currentProfile;

    private ArrayList<String> recipesAddList = new ArrayList<>();

    private JTable planTable, recipesTable;

    private Font profilesFont;
    private Font bigButtonFont, smallButtonFont;
    private Font dialogTextFont, dialogButtonFont, smallDialogButtonFont;

    private Dimension bigButtonSize, smallButtonSize, dialogSize;

    /**
     * Returns true if the frame is displayed correctly.
     *
     * @return true if frame is displayable, enabled, focusable and showing.
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

    /**
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

    /**
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

    /**
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
        dialogTextFont = new Font(SANS_SERIF, PLAIN, 19);
        dialogButtonFont = new Font(SANS_SERIF, PLAIN, 25);
        smallDialogButtonFont = new Font(SANS_SERIF, PLAIN, 16);
    }

    /**
     * loads login elements so that they can be accessed later.
     */
    private void loadLoginElements() {
        /*
         * create instances of the components.
         */
        profileLabel = new JLabel("Wähle bitte ein Profil aus:");
        profile1Button = new JButton(ProfileLoader.loadProfileButton("profile1"));
        profile2Button = new JButton(ProfileLoader.loadProfileButton("profile2"));
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
                currentProfile = ProfileLoader.loadProfile("profile1", mainFrame);
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
                currentProfile = ProfileLoader.loadProfile("profile2", mainFrame);
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

    /**
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
                mainFrame.add(ingredientsAddButton);
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

    /**
     * loads submenu elements so that they can be accessed later.
     */
    private void loadSubmenuElements() {
        /*
         * create instances of the components.
         */
        recipesInfoButton = new JButton("Rezepte anzeigen");
        recipesAddButton = new JButton("Rezept hinzufügen");
        ingredientsAddButton = new JButton("Zutat hinzufügen");
        profilesInfoButton = new JButton("Profil anzeigen");
        profilesEditButton = new JButton("Profil bearbeiten");
        profilesChangeButton = new JButton("Profil wechseln");
        /*
         * set size and position of components.
         */
        recipesInfoButton.setSize(smallButtonSize);
        recipesAddButton.setSize(smallButtonSize);
        ingredientsAddButton.setSize(smallButtonSize);
        profilesInfoButton.setSize(smallButtonSize);
        profilesEditButton.setSize(smallButtonSize);
        profilesChangeButton.setSize(smallButtonSize);
        recipesInfoButton.setLocation(0, 0);
        recipesAddButton.setLocation(0, mainFrame.getHeight() / 15);
        ingredientsAddButton.setLocation(0, mainFrame.getHeight() / 15 * 2);
        profilesInfoButton.setLocation(mainFrame.getWidth() / 4 * 3, 0);
        profilesEditButton.setLocation(mainFrame.getWidth() / 4 * 3, mainFrame.getHeight() / 15);
        profilesChangeButton.setLocation(mainFrame.getWidth() / 4 * 3, mainFrame.getHeight() / 15 * 2);
        /*
         * set decorations of components.
         */
        recipesInfoButton.setFont(smallButtonFont);
        recipesAddButton.setFont(smallButtonFont);
        ingredientsAddButton.setFont(smallButtonFont);
        profilesInfoButton.setFont(smallButtonFont);
        profilesEditButton.setFont(smallButtonFont);
        profilesChangeButton.setFont(smallButtonFont);
        recipesInfoButton.setBorderPainted(false);
        recipesAddButton.setBorderPainted(false);
        ingredientsAddButton.setBorderPainted(false);
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
                mainFrame.remove(ingredientsAddButton);
                mainFrame.add(recipesButton);
                mainFrame.repaint();
            }
        });
        recipesAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddButton.setEnabled(false);
                recipesAddDialog.getContentPane().removeAll();
                recipesAddDialog.add(recipesAddNameLabel);
                recipesAddNameTextField.setText("");
                recipesAddDialog.add(recipesAddNameTextField);
                recipesAddExistingList.setListData(RecipeLoader.getRecipeNames(RecipeLoader.loadAllRecipes()));
                recipesAddDialog.add(recipesAddExistingList);
                recipesAddDialog.add(recipesAddContinueButton);
                recipesAddDialog.add(recipesAddCancelButton);
                recipesAddDialog.setVisible(true);
            }
        });
        ingredientsAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingredientsAddButton.setEnabled(false);
                ingredientAddDialog.getContentPane().removeAll();
                ingredientAddDialog.add(ingredientAddLabel);
                ingredientAddDialog.add(ingredientAddTextField);
                ingredientAddList
                        .setListData(IngredientLoader.getIngredientNames(IngredientLoader.loadAllIngredients()));
                ingredientAddDialog.add(ingredientAddList);
                ingredientAddDialog.add(ingredientAddButton);
                ingredientAddDialog.add(ingredientAddCancelButton);
                ingredientAddDialog.setVisible(true);
                ingredientAddDialog.repaint();
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
                profile1Button.setText(ProfileLoader.loadProfileButton("profile1"));
                profile2Button.setText(ProfileLoader.loadProfileButton("profile2"));
                create();
            }
        });
    }

    /**
     * loads all dialog elements so that they can be accessed later.
     */
    private void loadAllDialogElements() {
        loadProfilesInfoDialog();
        loadProfilesEditDialog();
        loadRecipesAddDialog();
        loadRecipesAddSubDialog();
        loadAvailableIngredientsDialog();
        loadIngredientAddDialog();
        loadIngredientAddSubDialog();
    }

    /**
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
        profilesInfoHeightLabel.setFont(dialogTextFont);
        profilesInfoWeightLabel.setFont(dialogTextFont);
        profilesInfoAgeLabel.setFont(dialogTextFont);
        profilesInfoKcalLabel.setFont(dialogTextFont);
        profilesInfoGoalLabel.setFont(dialogTextFont);
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

    /**
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
        profilesEditHeightLabel.setFont(dialogTextFont);
        profilesEditWeightLabel.setFont(dialogTextFont);
        profilesEditAgeLabel.setFont(dialogTextFont);
        profilesEditKcalLabel.setFont(dialogTextFont);
        profilesEditGoalLabel.setFont(dialogTextFont);
        profilesEditHeightTextField.setFont(dialogTextFont);
        profilesEditWeightTextField.setFont(dialogTextFont);
        profilesEditAgeTextField.setFont(dialogTextFont);
        profilesEditKcalTextField.setFont(dialogTextFont);
        profilesEditGoalTextField.setFont(dialogTextFont);
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

    /**
     * loads recipes add dialog elements so that they can be accessed later.
     */
    private void loadRecipesAddDialog() {
        /*
         * create instances of the components.
         */
        recipesAddDialog = new JDialog(mainFrame, true);
        recipesAddNameLabel = new JLabel("Name des Rezepts: ");
        recipesAddNameTextField = new JTextField();
        recipesAddExistingList = new JList<>();
        recipesAddContinueButton = new JButton("Weiter");
        recipesAddCancelButton = new JButton("Abbrechen");
        /*
         * set size and position of components.
         */
        recipesAddDialog.setSize(dialogSize);
        recipesAddNameLabel.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 3);
        recipesAddNameTextField.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 6);
        recipesAddExistingList.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 3 * 2);
        recipesAddContinueButton.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 3);
        recipesAddCancelButton.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 3);
        recipesAddDialog.setLocationRelativeTo(mainFrame);
        recipesAddDialog.setLocation((int) recipesAddDialog.getLocation().getX() - recipesAddDialog.getWidth() / 2,
                (int) recipesAddDialog.getLocation().getY());
        recipesAddNameLabel.setLocation(0, 0);
        recipesAddNameTextField.setLocation(0,
                recipesAddNameLabel.getHeight() + recipesAddNameTextField.getHeight() / 2);
        recipesAddExistingList.setLocation(recipesAddDialog.getWidth() / 2, 0);
        recipesAddContinueButton.setLocation(0, recipesAddDialog.getHeight() / 3 * 2);
        recipesAddCancelButton.setLocation(recipesAddContinueButton.getWidth(), recipesAddDialog.getHeight() / 3 * 2);
        /*
         * set decorations of components.
         */
        recipesAddDialog.setLayout(null);
        recipesAddDialog.setUndecorated(true);
        recipesAddNameLabel.setHorizontalAlignment(CENTER);
        recipesAddContinueButton.setBorderPainted(false);
        recipesAddCancelButton.setBorderPainted(false);
        recipesAddNameLabel.setFont(dialogTextFont);
        recipesAddNameTextField.setFont(dialogTextFont);
        recipesAddExistingList.setFont(dialogTextFont);
        recipesAddContinueButton.setFont(dialogButtonFont);
        recipesAddCancelButton.setFont(dialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        recipesAddContinueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (recipesAddNameTextField.getText().isEmpty()) {
                    Dialog.wrongInputDialog(recipesAddDialog);
                    return;
                }
                boolean recipeExists = Tools.checkNameExists(recipesAddNameTextField.getText(),
                        recipesAddExistingList.getModel());
                if (recipeExists) {
                    JOptionPane.showMessageDialog(recipesAddDialog, "Rezept existiert bereits!", "Fehler!",
                            JOptionPane.WARNING_MESSAGE);
                }
                boolean recipeCouldExist = Tools.checkNameCouldExist(recipesAddNameTextField.getText(),
                        recipesAddExistingList.getModel());
                if (recipeCouldExist) {
                    recipesAddDialog.getContentPane().removeAll();
                    recipesAddDialog.add(recipesAddSimilarLabel);
                    recipesAddSimilarList.setListData(Tools.getSimilarNames(recipesAddNameTextField.getText(),
                            recipesAddExistingList.getModel()));
                    recipesAddDialog.add(recipesAddSimilarList);
                    recipesAddDialog.add(recipesAddYesButton);
                    recipesAddDialog.add(recipesAddNoButton);
                    recipesAddDialog.repaint();
                }
                if (!recipeExists && !recipeCouldExist) {
                    recipesAddDialog.getContentPane().removeAll();
                    recipesAddDialog.add(recipesAddIngredientsLabel);
                    recipesAddList.clear();
                    recipesAddIngredientsList.setListData(Tools.stringListToArray(recipesAddList));
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
                mainFrame.add(recipesButton);
                mainFrame.remove(recipesInfoButton);
                mainFrame.remove(recipesAddButton);
                mainFrame.remove(ingredientsAddButton);
                recipesAddButton.setEnabled(true);
                recipesAddDialog.setVisible(false);
                mainFrame.repaint();
            }
        });
    }

    // TODO sort out
    /**
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

        recipesAddSimilarLabel = new JLabel(
                "<html>Ähnlichkeiten gefunden!<br/>Existiert das neue Rezept schon?</html>");
        recipesAddSimilarList = new JList<>();
        recipesAddYesButton = new JButton("Ja");
        recipesAddNoButton = new JButton("Nein");

        recipesDescriptionLabel = new JLabel("Beschreibung hinzufügen:");
        // TODO textfield formatting
        recipesDescriptionTextField = new JTextField();
        recipesDescriptionFinishButton = new JButton("Fertig");
        recipesDescriptionCancelButton = new JButton("Abbrechen");
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

        recipesAddSimilarLabel.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 3 * 2);
        recipesAddSimilarList.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight());
        recipesAddYesButton.setSize(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 3);
        recipesAddNoButton.setSize(recipesAddDialog.getWidth() / 4, recipesAddDialog.getHeight() / 3);
        recipesAddSimilarLabel.setLocation(0, 0);
        recipesAddSimilarList.setLocation(recipesAddDialog.getWidth() / 2, 0);
        recipesAddYesButton.setLocation(0, recipesAddSimilarLabel.getHeight());
        recipesAddNoButton.setLocation(recipesAddYesButton.getWidth(), recipesAddSimilarLabel.getHeight());

        recipesDescriptionLabel.setSize(recipesAddDialog.getWidth(), recipesAddDialog.getHeight() / 5);
        recipesDescriptionTextField.setSize(recipesAddDialog.getWidth(), recipesAddDialog.getHeight() / 5 * 3);
        recipesDescriptionFinishButton.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 5);
        recipesDescriptionCancelButton.setSize(recipesAddDialog.getWidth() / 2, recipesAddDialog.getHeight() / 5);
        recipesDescriptionLabel.setLocation(0, 0);
        recipesDescriptionTextField.setLocation(0, recipesDescriptionLabel.getHeight());
        recipesDescriptionFinishButton.setLocation(0,
                recipesDescriptionLabel.getHeight() + recipesDescriptionTextField.getHeight());
        recipesDescriptionCancelButton.setLocation(recipesDescriptionFinishButton.getWidth(),
                recipesDescriptionLabel.getHeight() + recipesDescriptionTextField.getHeight());
        /*
         * set decorations of components.
         */
        recipesAddIngredientsLabel.setHorizontalAlignment(CENTER);
        recipesAddContinue2Button.setBorderPainted(false);
        recipesAddPlusButton.setBorderPainted(false);
        recipesAddMinusButton.setBorderPainted(false);
        recipesAddCancel2Button.setBorderPainted(false);
        recipesAddIngredientsLabel.setFont(dialogTextFont);
        recipesAddContinue2Button.setFont(smallDialogButtonFont);
        recipesAddPlusButton.setFont(smallDialogButtonFont);
        recipesAddMinusButton.setFont(smallDialogButtonFont);
        recipesAddCancel2Button.setFont(smallDialogButtonFont);

        recipesAddYesButton.setBorderPainted(false);
        recipesAddNoButton.setBorderPainted(false);
        recipesAddYesButton.setFont(smallDialogButtonFont);
        recipesAddNoButton.setFont(smallDialogButtonFont);
        recipesAddSimilarLabel.setHorizontalAlignment(CENTER);
        recipesAddSimilarLabel.setFont(dialogTextFont);
        recipesAddSimilarList.setFont(dialogTextFont);

        recipesDescriptionFinishButton.setBorderPainted(false);
        recipesDescriptionCancelButton.setBorderPainted(false);
        recipesDescriptionLabel.setHorizontalAlignment(CENTER);
        recipesDescriptionLabel.setFont(dialogTextFont);
        recipesDescriptionTextField.setFont(dialogTextFont);
        recipesDescriptionFinishButton.setFont(smallDialogButtonFont);
        recipesDescriptionCancelButton.setFont(smallDialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        recipesAddContinue2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!RecipeLoader.validateRecipe(recipesAddIngredientsList.getModel())) {
                    System.out.println("Mindestens 2 Zutaten müssen ausgewählt werden!");
                } else {
                    recipesAddDialog.getContentPane().removeAll();
                    recipesAddDialog.add(recipesDescriptionLabel);
                    recipesAddDialog.add(recipesDescriptionTextField);
                    recipesAddDialog.add(recipesDescriptionFinishButton);
                    recipesAddDialog.add(recipesDescriptionCancelButton);
                    recipesAddDialog.repaint();
                }
            }
        });
        recipesAddPlusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddPlusButton.setEnabled(false);
                availableIngredientsDialog.getContentPane().removeAll();
                availableIngredientsDialog.add(availableIngredientsLabel);
                availableIngredientsDialog.add(availableIngredientsList);
                availableIngredientsList.setListData(IngredientLoader.loadAllIngredients());
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
                recipesAddIngredientsList.setListData(Tools.stringListToArray(recipesAddList));
                recipesAddDialog.repaint();
            }
        });
        recipesAddCancel2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.add(recipesButton);
                mainFrame.remove(recipesInfoButton);
                mainFrame.remove(recipesAddButton);
                mainFrame.remove(ingredientsAddButton);
                recipesAddButton.setEnabled(true);
                recipesAddDialog.setVisible(false);
                mainFrame.repaint();
            }
        });

        recipesAddYesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddButton.setEnabled(false);
                recipesAddDialog.getContentPane().removeAll();
                recipesAddDialog.add(recipesAddNameLabel);
                recipesAddNameTextField.setText("");
                recipesAddDialog.add(recipesAddNameTextField);
                recipesAddExistingList.setListData(RecipeLoader.getRecipeNames(RecipeLoader.loadAllRecipes()));
                recipesAddDialog.add(recipesAddExistingList);
                recipesAddDialog.add(recipesAddContinueButton);
                recipesAddDialog.add(recipesAddCancelButton);
                recipesAddDialog.repaint();
            }
        });
        recipesAddNoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recipesAddDialog.getContentPane().removeAll();
                recipesAddDialog.add(recipesAddIngredientsLabel);
                recipesAddList.clear();
                recipesAddIngredientsList.setListData(Tools.stringListToArray(recipesAddList));
                recipesAddDialog.add(recipesAddIngredientsList);
                recipesAddDialog.add(recipesAddPlusButton);
                recipesAddDialog.add(recipesAddMinusButton);
                recipesAddDialog.add(recipesAddContinue2Button);
                recipesAddDialog.add(recipesAddCancel2Button);
                recipesAddDialog.repaint();
            }
        });

        recipesDescriptionFinishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO save recipe
            }
        });
        recipesDescriptionCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO cancel recipe
            }
        });
    }

    /**
     * loads available ingredients dialog elements
     * so that they can be accessed later.
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
        availableIngredientsLabel.setFont(dialogTextFont);
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
                            input = JOptionPane.showInputDialog(availableIngredientsDialog, "Wie viel "
                                    + availableIngredientsList.getSelectedValue().getName()
                                    + " möchtest du hinzufügen?",
                                    "Bitte Menge in gramm angeben!", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break getInput;
                            }
                            if (!Tools.checkDouble(input)) {
                                Dialog.wrongInputDialog(availableIngredientsDialog);
                            } else {
                                recipesAddList
                                        .add(input + "g " + availableIngredientsList.getSelectedValue().getName());
                                break getInput;
                            }
                        }
                    case 2:
                        while (true) {
                            input = JOptionPane.showInputDialog(availableIngredientsDialog, "Wie viel "
                                    + availableIngredientsList.getSelectedValue().getName()
                                    + " möchtest du hinzufügen?",
                                    "Bitte Menge in ml angeben!", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break getInput;
                            }
                            if (!Tools.checkDouble(input)) {
                                Dialog.wrongInputDialog(availableIngredientsDialog);
                            } else {
                                recipesAddList
                                        .add(input + "ml " + availableIngredientsList.getSelectedValue().getName());
                                break getInput;
                            }
                        }
                    case 3:
                        while (true) {
                            input = JOptionPane.showInputDialog(availableIngredientsDialog, "Wie viel "
                                    + availableIngredientsList.getSelectedValue().getName()
                                    + " möchtest du hinzufügen?",
                                    "Bitte Menge in Stückzahl angeben!", JOptionPane.QUESTION_MESSAGE);
                            if (input == null) {
                                break getInput;
                            }
                            if (!Tools.checkDouble(input)) {
                                Dialog.wrongInputDialog(availableIngredientsDialog);
                            } else {
                                recipesAddList
                                        .add(input + "stk. " + availableIngredientsList.getSelectedValue().getName());
                                break getInput;
                            }
                        }
                }
                recipesAddIngredientsList.setListData(Tools.stringListToArray(recipesAddList));
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

    /**
     * loads ingredient add dialog elements
     * so that they can be accessed later.
     */
    private void loadIngredientAddDialog() {
        /*
         * create instances of the components.
         */
        ingredientAddDialog = new JDialog(mainFrame, true);
        ingredientAddLabel = new JLabel("Name:");
        ingredientAddTextField = new JTextField();
        ingredientAddList = new JList<>();
        ingredientAddButton = new JButton("Hinzufügen");
        ingredientAddCancelButton = new JButton("Abbrechen");
        /*
         * set size and position of components.
         */
        ingredientAddDialog.setSize(dialogSize);
        ingredientAddLabel.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight() / 2);
        ingredientAddTextField.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight() / 6);
        ingredientAddList.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight() / 3 * 2);
        ingredientAddButton.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight() / 3);
        ingredientAddCancelButton.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight() / 3);
        ingredientAddDialog.setLocationRelativeTo(mainFrame);
        ingredientAddLabel.setLocation(0, 0);
        ingredientAddTextField.setLocation(0,
                ingredientAddDialog.getHeight() / 3 + ingredientAddDialog.getHeight() / 12);
        ingredientAddList.setLocation(ingredientAddDialog.getWidth() / 2, 0);
        ingredientAddButton.setLocation(0, ingredientAddDialog.getHeight() / 3 * 2);
        ingredientAddCancelButton.setLocation(ingredientAddDialog.getWidth() / 2,
                ingredientAddDialog.getHeight() / 3 * 2);
        /*
         * set decorations of components.
         */
        ingredientAddDialog.setLayout(null);
        ingredientAddDialog.setUndecorated(true);
        ingredientAddButton.setBorderPainted(false);
        ingredientAddCancelButton.setBorderPainted(false);
        ingredientAddLabel.setHorizontalAlignment(CENTER);
        ingredientAddList.setFont(dialogTextFont);
        ingredientAddLabel.setFont(dialogTextFont);
        ingredientAddTextField.setFont(dialogTextFont);
        ingredientAddButton.setFont(dialogButtonFont);
        ingredientAddCancelButton.setFont(dialogButtonFont);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        ingredientAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*
                 * show an error message if there is no input when the button is pressed.
                 */
                if (ingredientAddTextField.getText().isEmpty() || ingredientAddTextField == null) {
                    Dialog.wrongInputDialog(ingredientAddDialog);
                    return;
                }
                boolean ingredientExists = Tools.checkNameExists(ingredientAddTextField.getText(),
                        ingredientAddList.getModel());
                if (ingredientExists) {
                    JOptionPane.showMessageDialog(ingredientAddDialog, "Zutat existiert bereits!", "Fehler!",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                boolean ingredientCouldExist = Tools.checkNameCouldExist(ingredientAddTextField.getText(),
                        ingredientAddList.getModel());
                if (ingredientCouldExist) {
                    ingredientAddDialog.getContentPane().removeAll();
                    ingredientAddDialog.add(ingredientAddSimilarLabel);
                    ingredientAddSimilarList.setListData(
                            Tools.getSimilarNames(ingredientAddTextField.getText(), ingredientAddList.getModel()));
                    ingredientAddDialog.add(ingredientAddSimilarList);
                    ingredientAddDialog.add(ingredientAddYesButton);
                    ingredientAddDialog.add(ingredientAddNoButton);
                    ingredientAddDialog.repaint();
                }
                if (!ingredientExists && !ingredientCouldExist) {
                    ingredientAddDialog.getContentPane().removeAll();
                    ingredientAddDialog.setVisible(false);
                    ingredientAddDialog.repaint();
                    ingredientsAddButton.setEnabled(true);
                    Ingredient ing = Dialog.getIngredientInformation(ingredientAddTextField.getText(), mainFrame);
                    if (ing == null) {
                        ingredientAddTextField.setText("");
                        mainFrame.add(recipesButton);
                        mainFrame.remove(recipesInfoButton);
                        mainFrame.remove(recipesAddButton);
                        mainFrame.remove(ingredientsAddButton);
                        mainFrame.repaint();
                    } else {
                        IngredientLoader.saveIngredient(ing);
                        ingredientAddTextField.setText("");
                        mainFrame.add(recipesButton);
                        mainFrame.remove(recipesInfoButton);
                        mainFrame.remove(recipesAddButton);
                        mainFrame.remove(ingredientsAddButton);
                        mainFrame.repaint();
                    }
                }
            }
        });
        ingredientAddCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.add(recipesButton);
                mainFrame.remove(recipesInfoButton);
                mainFrame.remove(recipesAddButton);
                mainFrame.remove(ingredientsAddButton);
                ingredientsAddButton.setEnabled(true);
                ingredientAddDialog.setVisible(false);
                mainFrame.repaint();
            }
        });
    }

    /**
     * loads ingredient add sub dialog elements
     * so that they can be accessed later.
     */
    private void loadIngredientAddSubDialog() {
        /*
         * create instances of the components.
         */
        ingredientAddSimilarLabel = new JLabel(
                "<html>Ähnlichkeiten gefunden!<br/>Existiert die neue Zutat schon?</html>");
        ingredientAddSimilarList = new JList<>();
        ingredientAddYesButton = new JButton("Ja");
        ingredientAddNoButton = new JButton("Nein");
        /*
         * set size and position of components.
         */
        ingredientAddSimilarLabel.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight() / 3 * 2);
        ingredientAddSimilarList.setSize(ingredientAddDialog.getWidth() / 2, ingredientAddDialog.getHeight());
        ingredientAddYesButton.setSize(ingredientAddDialog.getWidth() / 4, ingredientAddDialog.getHeight() / 3);
        ingredientAddNoButton.setSize(ingredientAddDialog.getWidth() / 4, ingredientAddDialog.getHeight() / 3);
        ingredientAddSimilarLabel.setLocation(0, 0);
        ingredientAddSimilarList.setLocation(ingredientAddDialog.getWidth() / 2, 0);
        ingredientAddYesButton.setLocation(0, ingredientAddSimilarLabel.getHeight());
        ingredientAddNoButton.setLocation(ingredientAddYesButton.getWidth(), ingredientAddSimilarLabel.getHeight());
        /*
         * set decorations of components.
         */
        ingredientAddSimilarLabel.setFont(dialogTextFont);
        ingredientAddSimilarList.setFont(dialogTextFont);
        ingredientAddYesButton.setFont(dialogButtonFont);
        ingredientAddNoButton.setFont(dialogButtonFont);
        ingredientAddYesButton.setBorderPainted(false);
        ingredientAddNoButton.setBorderPainted(false);
        /*
         * add listener to buttons, those decide what actions happen
         * when buttons are pressed.
         */
        ingredientAddYesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingredientAddDialog.getContentPane().removeAll();
                ingredientAddDialog.add(ingredientAddLabel);
                ingredientAddDialog.add(ingredientAddTextField);
                ingredientAddTextField.setText("");
                ingredientAddDialog.add(ingredientAddList);
                ingredientAddDialog.add(ingredientAddButton);
                ingredientAddDialog.add(ingredientAddCancelButton);
                ingredientAddDialog.repaint();
            }
        });
        ingredientAddNoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingredientAddDialog.getContentPane().removeAll();
                ingredientAddDialog.setVisible(false);
                ingredientAddDialog.repaint();
                ingredientsAddButton.setEnabled(true);
                Ingredient ing = Dialog.getIngredientInformation(ingredientAddTextField.getText(), mainFrame);
                if (ing == null) {
                    ingredientAddTextField.setText("");
                    mainFrame.add(recipesButton);
                    mainFrame.remove(recipesInfoButton);
                    mainFrame.remove(recipesAddButton);
                    mainFrame.remove(ingredientsAddButton);
                    mainFrame.repaint();
                } else {
                    IngredientLoader.saveIngredient(ing);
                    ingredientAddTextField.setText("");
                    mainFrame.add(recipesButton);
                    mainFrame.remove(recipesInfoButton);
                    mainFrame.remove(recipesAddButton);
                    mainFrame.remove(ingredientsAddButton);
                    mainFrame.repaint();
                }
            }
        });
    }
}
