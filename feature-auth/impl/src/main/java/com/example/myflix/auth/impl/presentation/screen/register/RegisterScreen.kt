package com.example.myflix.auth.impl.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myflix.design_system.R
import com.example.myflix.design_system.domain.model.InputWrapper
import com.example.myflix.design_system.domain.model.PartialClickableItems
import com.example.myflix.design_system.domain.model.PartialClickableTextType
import com.example.myflix.design_system.presentation.component.FLixTextField
import com.example.myflix.design_system.presentation.component.FlixButton
import com.example.myflix.design_system.presentation.component.FlixIconButton
import com.example.myflix.design_system.presentation.component.FlixPartialClickableText
import com.example.myflix.design_system.presentation.theme.Gray
import com.example.myflix.design_system.presentation.theme.Gray15

@Composable
fun RegisterScreen(
    onToLoginScreen: () -> Unit
) {

    var nameInput by remember {
        mutableStateOf(InputWrapper(""))
    }
    var emailInput by remember {
        mutableStateOf(InputWrapper(""))
    }
    var passwordInput by remember {
        mutableStateOf(InputWrapper(""))
    }
    var confirmPasswordInput by remember {
        mutableStateOf(InputWrapper(""))
    }
    var isShouldShowPassword by remember {
        mutableStateOf(false)
    }
    var isShouldShowConfirmPassword by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            modifier = Modifier.padding(start = 24.dp, top = 70.dp),
            painter = painterResource(id = R.drawable.ic_myflix),
            contentDescription = null,
            tint = Color.Unspecified
        )
        FLixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 36.dp),
            input = nameInput,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            visualTransformation = VisualTransformation.None,
            label = com.example.myflix.auth.impl.R.string.name_text,
            placeholder = com.example.myflix.auth.impl.R.string.name_placeholder,
            onValueChange = {
                nameInput = nameInput.copy(it)
            }
        )
        FLixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp),
            input = emailInput,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            visualTransformation = VisualTransformation.None,
            label = com.example.myflix.auth.impl.R.string.email_address_txt,
            placeholder = com.example.myflix.auth.impl.R.string.email_address_placeholder,
            onValueChange = {
                emailInput = emailInput.copy(it)
            }
        )
        FLixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp),
            input = passwordInput,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isShouldShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isShouldShowPassword = !isShouldShowPassword
                    }
                ) {
                    Icon(
                        imageVector = if (isShouldShowPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        tint = Gray
                    )
                }
            },
            label = com.example.myflix.auth.impl.R.string.password_txt,
            placeholder = com.example.myflix.auth.impl.R.string.password_placeholder,
            onValueChange = {
                passwordInput = passwordInput.copy(it)
            }
        )
        FLixTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 20.dp),
            input = confirmPasswordInput,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (isShouldShowConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isShouldShowConfirmPassword = !isShouldShowConfirmPassword
                    }
                ) {
                    Icon(
                        imageVector = if (isShouldShowConfirmPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null,
                        tint = Gray
                    )
                }
            },
            label = com.example.myflix.auth.impl.R.string.confirm_password_txt,
            placeholder = com.example.myflix.auth.impl.R.string.confirm_password_placeholder,
            onValueChange = {
                confirmPasswordInput = confirmPasswordInput.copy(it)
            }
        )

        FlixButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 36.dp),
            buttonText = com.example.myflix.auth.impl.R.string.sign_up_txt
        ) {
            // TODO: On sign in button click
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            text = stringResource(id = com.example.myflix.auth.impl.R.string.or_txt),
            style = MaterialTheme.typography.labelMedium,
            color = Gray
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            FlixIconButton(
                modifier = Modifier,
                contentColor = Gray,
                buttonColor = Gray15,
                icon = R.drawable.ic_google
            ) {

            }
            FlixIconButton(
                modifier = Modifier,
                contentColor = Gray,
                buttonColor = Gray15,
                icon = R.drawable.ic_facebook
            ) {

            }
            FlixIconButton(
                modifier = Modifier,
                contentColor = Gray,
                buttonColor = Gray15,
                icon = R.drawable.ic_twitter
            ) {

            }
        }
        Spacer(modifier = Modifier.weight(1f))
        FlixPartialClickableText(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 56.dp, bottom = 32.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            items = listOf(
                PartialClickableItems(
                    text = stringResource(id = com.example.myflix.auth.impl.R.string.have_an_account_txt),
                    color = Gray,
                    type = PartialClickableTextType.NORMAL
                ),
                PartialClickableItems(
                    text = stringResource(id = com.example.myflix.auth.impl.R.string.sign_in_txt),
                    color = MaterialTheme.colorScheme.primary,
                    type = PartialClickableTextType.CLICKABLE,
                    onClick = {
                        onToLoginScreen.invoke()
                    }
                )
            )
        )
    }
}