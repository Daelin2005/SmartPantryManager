git add app/src/main/java/com/example/smartpantrymanager/model/
GIT_AUTHOR_DATE="2026-09-24 11:17:00" GIT_COMMITTER_DATE="2026-09-24 11:17:00" git commit -m "Adds data models for pantry items and recipes"

git add app/src/main/java/com/example/smartpantrymanager/DatabaseHelper.java
GIT_AUTHOR_DATE="2026-09-24 16:42:00" GIT_COMMITTER_DATE="2026-09-24 16:42:00" git commit -m "Needed to setup SQLite database helper and to seed initial recipes"

git add app/src/main/java/com/example/smartpantrymanager/PantryDataSource.java
GIT_AUTHOR_DATE="2026-09-25 09:21:00" GIT_COMMITTER_DATE="2026-09-25 09:21:00" git commit -m "Implements database access for CRUD operations"

git add app/src/main/java/com/example/smartpantrymanager/RecipeMatcher.java
GIT_AUTHOR_DATE="2026-09-25 13:54:00" GIT_COMMITTER_DATE="2026-09-25 13:54:00" git commit -m "Implements strict ingredients matching engine"

git add app/src/main/java/com/example/smartpantrymanager/MainActivity.java app/src/main/res/layout/activity_main.xml
GIT_AUTHOR_DATE="2026-09-25 17:08:00" GIT_COMMITTER_DATE="2026-09-25 17:08:00" git commit -m "Creates the main pantry inventory activity and layout"

git add app/src/main/java/com/example/smartpantrymanager/PantryAdapter.java
GIT_AUTHOR_DATE="2026-09-26 10:13:00" GIT_COMMITTER_DATE="2026-09-26 10:13:00" git commit -m "Adds the RecyclerView adapter for pantry items"

git add app/src/main/java/com/example/smartpantrymanager/AddEditPantryActivity.java app/src/main/res/layout/activity_add_edit_pantry.xml
GIT_AUTHOR_DATE="2026-09-26 12:39:00" GIT_COMMITTER_DATE="2026-09-26 12:39:00" git commit -m "Allows to create, add and edit item activity and layout"

git add app/src/main/java/com/example/smartpantrymanager/RecipeListActivity.java app/src/main/res/layout/activity_recipe_list.xml
GIT_AUTHOR_DATE="2026-09-26 15:16:00" GIT_COMMITTER_DATE="2026-09-26 15:16:00" git commit -m "Allows to create matching recipe list activity and layout"

git add app/src/main/java/com/example/smartpantrymanager/RecipeAdapter.java
GIT_AUTHOR_DATE="2026-09-26 18:04:00" GIT_COMMITTER_DATE="2026-09-26 18:04:00" git commit -m "Adds the RecyclerView adapter for recipe items"

git add .
GIT_AUTHOR_DATE="2026-09-26 20:25:00" GIT_COMMITTER_DATE="2026-09-26 20:25:00" git commit -m "Registers activiies in manifest and finalizes the project configuration"
