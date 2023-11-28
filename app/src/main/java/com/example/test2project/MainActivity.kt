package com.example.test2project

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test2project.Data.Entry
import com.example.test2project.Data.entrydata
import com.example.test2project.Data.entrydata.entryList
import com.example.test2project.Screens.MainScreen
import com.example.test2project.ui.theme.Test2projectTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test2projectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

// Used to compare register and login data
var registerusername = ""
var registerpassword = ""
var registered : Boolean = false

// Used to save the id of the clicked entry
var compareid : Int = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var showInvalidDataError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (username.isEmpty() || password.isEmpty() || !registered) {
                    showError = true
                    showInvalidDataError = false
                } else if ((username != registerusername) || (password != registerpassword)) {
                    showError = false
                    showInvalidDataError = true

            } else {
                showError = false
                showInvalidDataError = false
                navController.navigate("HomeScreen")
                }
            }
        ) {
            Text("Login")
        }
        if (showError) {
            Text(
                text = "Invalid! Enter username and password or register!",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        if (showInvalidDataError) {
            Text(
                text = "Invalid username or password!",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                    registered = true
                    navController.navigate("RegisterScreen")
            }
        ) {
            Text("Register")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (username.isEmpty() || password.isEmpty()) {
                    showError = true
                } else {
                    registered = true
                    registerusername = username
                    registerpassword = password
                    navController.navigate("LoginScreen")
                }
            }
        ) {
            Text("Register")
        }
        if (showError) {
            Text(
                text = "Please enter username and password",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable      // Getting entries from EntryList function and displaying them in list form
fun List(entry: Entry,
         modifier: Modifier = Modifier,
         onItemClick: () -> Unit,
         backgroundColor: Color)
{
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .clickable { onItemClick() }
            .padding(8.dp)
            .background(backgroundColor)
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = entry.photo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .padding(2.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))

            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = entry.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = entry.category,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryList(entries: MutableList<Entry>, navController : NavController) {   // Putting entries in the List() function
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Scaffold(
            topBar = {
                TopAppBar("Entries", backButton = false, goBackScreen = "", navController = navController)
            },
            floatingActionButton = {
                ActionButton(navController)
            }

        ) { contentPadding ->

            LazyColumn (
                contentPadding = contentPadding
            ) {
                items(entries) { entry ->
                    List(
                        entry,
                        backgroundColor = Color.Transparent,
                        onItemClick = {
                            compareid = entry.id
                            navController.navigate("EntryDataScreen")
                        }
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")     // Shown when an entry is clicked on the main page
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPage(name: String, email: String, password: String, @DrawableRes photo: Int, navController: NavController) {
    var passwordVisibility by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                "Entry Page",
                 backButton = true,
                navController = navController,
                goBackScreen = "HomeScreen",
            )
        }
    ) {
            Column(
                modifier = Modifier.padding(top = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                    // Upper half - Photo
                    Image(
                        painter = painterResource(photo),
                        contentDescription = "Entry Photo",
                        modifier = Modifier
                            .size(130.dp),
                        contentScale = ContentScale.Crop
                    )

                Spacer(modifier = Modifier.height(180.dp))

                Column(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 20.dp)

                ) {

                    // Lower half - Information
                    Text(
                        text = "Name:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$name",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(
                        color = Color.Black.copy(alpha = 0.2f),
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    Text(
                        text = "Email / username:",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$email",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 23.sp
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(
                        color = Color.Black.copy(alpha = 0.2f),
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    Text(
                        text = "Password:",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        if (passwordVisibility) {
                            Text(
                                text = password.replace(Regex("."), "*"),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 23.sp,
                                modifier = Modifier
                                    .weight(1f)
                            )

                            IconButton(
                                onClick = { passwordVisibility = !passwordVisibility },
                                modifier = Modifier.padding(end = 25.dp),
                            ) {
                                Icon(
                                    imageVector =  Icons.Default.Lock,
                                    contentDescription = "Hide/Show Password",
                                )
                            }

                        } else {
                            Text(
                                text = password,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 23.sp,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            IconButton(
                                onClick = { passwordVisibility = !passwordVisibility },
                                modifier = Modifier.padding(end = 25.dp)
                            ) {
                                Icon(
                                    imageVector =  Icons.Filled.Warning,
                                    contentDescription = "Hide/Show Password",
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(
                        color = Color.Black.copy(alpha = 0.2f),
                        modifier = Modifier.padding(end = 20.dp)
                    )

                    Row() {
                        Button(
                            onClick = { navController.navigate("editEntryScreen") },
                            modifier = Modifier
                                .padding(top = 45.dp),
                            contentPadding = PaddingValues(8.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF1944dc))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Entry",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(text = "Edit Entry", color = Color.White)
                            }
                        }

                        Button(
                            onClick = {
                                val entryToRemove = entryList.find { it.id == compareid }
                                    entryList.remove(entryToRemove)
                                navController.navigate("HomeScreen")
                            },
                            modifier = Modifier.padding(start = 130.dp, top = 45.dp),
                            contentPadding = PaddingValues(8.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFd71919))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Entry",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(text = "Delete Entry", color = Color.White)
                            }
                        }
                    }
                }
            }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addEntry(navController: NavController) {     // Shown when floating action button is clicked
    var selectedImageUri: Uri? by remember { mutableStateOf(null) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

    Scaffold(
        topBar = {
            TopAppBar(
                "Add Entry",
                backButton = true,
                goBackScreen = "HomeScreen",
                navController = navController,
            )
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Upper half - Photo space and button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                if (selectedImageUri == null) {
                    Text(text = "No photo selected")
                } else {
                    bitmap.value?.let { btm ->
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = "Selected Photo",
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // Button to choose a photo
            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.padding(start = 25.dp)
            ) {
                Text(text = "Choose Photo")
            }

            // Lower half - Text fields for Name, Email, and Password
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 5.dp, start = 25.dp)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text(text = "Category") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(
                                imageVector =  Icons.Default.Lock,
                                contentDescription = "Hide/Show Password",
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )
                var nextid = 9  // Default number of entries is 8, so when adding a new entry 9 should be the next entry id (cannot be initialised in onclick, since then id will always be 9 when button is clicked)
                Button(
                    onClick = {
                        val newEntry = Entry(nextid++, name, category, R.drawable.default_placeholder)
                        entryList.add(newEntry)
                        navController.navigate("HomeScreen")
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 25.dp, end = 25.dp),
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF29292b))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Entry",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "Add Entry", color = Color.White)
                    }

                }

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun editEntry(navController: NavController) {     // Shown when edit entry button is clicked
    var selectedImageUri: Uri? by remember { mutableStateOf(null) }
    var name by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    var photo = 0

    when(compareid) {
        1 -> {
            name = "Facebook"
            category = "Social Media"
            email = "facebook@fb.com"
            password = "facebook"
            photo = R.drawable.facebook_logo
        }
        2 -> {
            name = "Twitter"
            category = "Social Media"
            email = "twitter@tw.com"
            password = "twitter"
            photo = R.drawable._0wmt_articlelarge_v4_jpg
        }
        3 -> {
            name = "Klix"
            category = "News"
            email = "klix@klix.com"
            password = "klix"
            photo = R.drawable.klix
        }
        4 -> {
            name = "GitHub"
            category = "Education"
            email = "github@gh.com"
            password = "github"
            photo = R.drawable.github_logo
        }
        5 -> {
            name = "Spotify"
            category = "Entertainment"
            email = "spotify@sp.com"
            password = "spotify"
            photo = R.drawable.spotify_logo_without_text
        }
        6 -> {
            name = "IBU"
            category = "Education"
            email = "ibu@ibu.com"
            password = "Burch"
            photo = R.drawable.ibu_logo_user
        }
        7 -> {
            name = "Linkedin"
            category = "Education"
            email = "linkedin@ln.com"
            password = "linkedin"
            photo = R.drawable.linkedin_logo_initials
        }
        8 -> {
            name = "Gmail"
            category = "Email"
            email = "gmail@gmail.com"
            password = "gmail"
            photo = R.drawable.gmail_logo
        }
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri
        }

    Scaffold(
        topBar = {
            TopAppBar(
                "Edit Entry",
                backButton = true,
                goBackScreen = "EntryDataScreen",
                navController = navController,
            )
        }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Upper half - Photo space and button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                        Image(
                            painter = painterResource(photo),
                            contentDescription = "Selected Photo",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
            }

            // Button to choose a photo
            Button(
                onClick = { launcher.launch("image/*") },
                modifier = Modifier.padding(start = 25.dp)
            ) {
                Text(text = "Choose Photo")
            }

            // Lower half - Text fields for Name, Email, and Password
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 5.dp, start = 25.dp)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text(text = "Category") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth()
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .padding(top = 15.dp, end = 25.dp)
                        .fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility }
                        ) {
                            Icon(
                                imageVector =  if (passwordVisibility) Icons.Default.Lock else Icons.Default.Warning,
                                contentDescription = "Hide/Show Password",
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
                )

                Button(
                    onClick = {
                        val updatedEntry = Entry(compareid, name, category, photo)
                        val indexToUpdate = entryList.indexOfFirst { it.id == compareid }
                            entryList[indexToUpdate] = updatedEntry
                        navController.navigate("HomeScreen")
                              },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 45.dp, end = 25.dp),
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF29292b))
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Confirm Edit",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "Confirm", color = Color.White)
                    }
                }

            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneratePassword(navController: NavController) {
    var generatedPassword by remember { mutableStateOf("") }
    var passwordHealth by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                "Password Generator",
                backButton = false,
                goBackScreen = "",
                navController = navController,
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Generated Password: $generatedPassword")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { generatedPassword = generateRandomPassword() }) {
                Text("Generate Password")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Password Health: $passwordHealth")
        }

        passwordHealth = if (generatedPassword.length <= 6) "Weak"
        else if (generatedPassword.length <= 10 && generatedPassword.length > 6) "Moderate"
        else "Strong"
    }
}

private fun generateRandomPassword(): String {

    val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-="
    val minLength = 4
    val maxLength = 14
    val passwordLength = Random.nextInt(minLength, maxLength + 1)
    val password = (1..passwordLength)
        .map { characters[Random.nextInt(characters.length)] }
        .joinToString("")
    return password
}

fun fetchEntryList(): MutableList<Entry> {    // Fetch all the entries (id, title, category, photo)
    return entryList
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    name: String,
    backButton: Boolean,                     // If true, screen has back button
    goBackScreen: String,                   // This screen is opened when back button is clicked
    navController: NavController,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (!backButton) {                          // No back button
        androidx.compose.material3.TopAppBar(
            title = { Text(text = name) },
            actions = {
                IconButton(
                    onClick = { if (name == "Entries") navController.navigate("GeneratePasswordScreen")
                    else navController.navigate("HomeScreen") }
                ) {
                    Icon(
                        if (name == "Entries") Icons.Outlined.Build
                        else Icons.Outlined.Home,
                        contentDescription = "Home"
                    )
                }
                IconButton(
                    onClick = { showDialog = true }
                ) {
                    Icon(
                        Icons.Outlined.ExitToApp,
                        contentDescription = "Log out"
                    )
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text(text = "Log Out") },
                        text = { Text(text = "Are you sure you want to log out?") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    navController.navigate("LoginScreen")
                                    showDialog = false
                                }
                            ) {
                                Text(text = "Log Out")
                            }
                        },
                        dismissButton = {
                            Button(
                                onClick = { showDialog = false }
                            ) {
                                Text(text = "Cancel")
                            }
                        },
                    )
                }
            }
        )
    }

    else {                                        // Top bar with back arrow
        androidx.compose.material3.TopAppBar(
            title = { Text(text = name) },
            navigationIcon = {
                IconButton(onClick = {
                        navController.navigate(goBackScreen)
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                }
            }
        )
    }
}


@Composable
fun ActionButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("addEntryScreen")
        },
        content = {
            Icon(Icons.Default.Add, contentDescription = "Add Item")
        },
        modifier = Modifier
            .padding(16.dp)
            .size(55.dp)
    )
}

