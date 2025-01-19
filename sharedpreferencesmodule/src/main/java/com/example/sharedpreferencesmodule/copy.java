package com.example.sharedpreferencesmodule;

public class copy {
    /*
        private void loadData_(TextInputLayout... texts) {
        SharedPreferences getSharedPreferences = getSharedPreferences(Profile.PREFS_NAME, MODE_PRIVATE);
        // Извлекает данные по ключу "name". Если значение отсутствует, возвращается defValue.
        texts[0].getEditText().setText(getSharedPreferences.getString(Profile.KEY_USERNAME, Profile.DEF_USERNAME));
        texts[1].getEditText().setText(getSharedPreferences.getString(Profile.KEY_EMAIL, Profile.DEF_EMAIL));
        texts[2].getEditText().setText(getSharedPreferences.getString(Profile.KEY_PASSWORD, Profile.DEF_PASSWORD));

        // on below line we are displaying a toast message after adding data to shared prefs.
        Toast.makeText(this, "Загружено", Toast.LENGTH_SHORT).show();
    }

    private void deleteData_() {
        SharedPreferences delSharedPreferences = getSharedPreferences(Profile.PREFS_NAME, MODE_PRIVATE);
        // Получает объект SharedPreferences.Editor, который позволяет редактировать данные.
        SharedPreferences.Editor delEditor = delSharedPreferences.edit();
        // Удаляет все данные
        delEditor.clear();
        delEditor.apply();
        // on below line we are displaying a toast message after adding data to shared prefs.
        Toast.makeText(this, "Удалено", Toast.LENGTH_SHORT).show();
    }

    // Мы ограничены ключами
    private void saveData_(String... texts) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // below lines will put values for
        // message in shared preferences.
        editor.putString(Profile.KEY_USERNAME, texts[0]);
        editor.putString(Profile.KEY_EMAIL, texts[1]);
        editor.putString(Profile.KEY_PASSWORD, texts[2]);
        // to save our data with key and value.
        editor.apply();

        // on below line we are displaying a toast message after adding data to shared prefs.
        Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
    }    // Альтернативный метод для валидации полей (дублирующий функциональность)
    private boolean validateInputTexts(TextInputLayout... texts) {
        boolean isValid = true;

        for (TextInputLayout text : texts) {
            if (TextUtils.isEmpty(text.getEditText().getText().toString())) {
                text.setError("Заполните поле");
                isValid = false;
            } else {
                text.setError(null);
            }
        }
        return isValid;
    }
     */
}
