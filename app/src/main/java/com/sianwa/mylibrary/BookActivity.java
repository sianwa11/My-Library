package com.sianwa.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddToWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavourites;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (null != incomingBook) {
                    setData(incomingBook);

                    handleAlreadyReadBooks(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleCurrentlyReadingBooks(Book incomingBook) {
        ArrayList<Book> currentlyReading = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b : currentlyReading) {
            if (b.getId() == incomingBook.getId()) {
                existInCurrentlyReadingBooks = true;
            }
        }

        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        } else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToCurrentlyReading(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBooksActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavouriteBooks(Book incomingBook) {
        ArrayList<Book> favouriteBooks = Utils.getInstance().getFavouriteBooks();

        boolean existInFavouriteBooks = false;

        for (Book b : favouriteBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInFavouriteBooks = true;
            }
        }

        if (existInFavouriteBooks) {
            btnAddToFavourites.setEnabled(false);
        } else {
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToFavourites(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavouriteBooksActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks) {
            if (b.getId() == incomingBook.getId()) {
                existInWantToReadBooks = true;
            }
        }

        if (existInWantToReadBooks) {
            btnAddToWantToRead.setEnabled(false);
        } else {
            btnAddToWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToWantToRead(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadBookActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyReadBooks(Book incomingBook) {
        ArrayList<Book> alreadyRead = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyRead) {
            if (b.getId() == incomingBook.getId()) {
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks) {
            btnAddToAlreadyRead.setEnabled(false);
        } else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.getInstance().addToAlreadyRead(incomingBook)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book) {
        //TODO:Get incoming data from recview;
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavourites = findViewById(R.id.btnAddToFav);

        bookImage = findViewById(R.id.bookImage);
    }
}