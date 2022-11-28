const fullL10n = {
    en: {
        museumProfile: {
            category: 'Category',
            contact: 'Contact',
            tours: 'Upcoming Tours',
            noTours: 'This museum does not have any upcoming tours.'
        },
        weatherPanel: {
            endpointSlug: 'en',
            feelsLike: ', feels like '
        },
        header: {
            museums: 'Museums',
            tours: 'Tours',
            visit: 'Visit',
            welcome: 'Welcome',
            messages: 'Messages',
            addMuseums: 'Add Museums',
            stats: 'Stats',
            users: 'Users',
            signIn: 'Sign In',
            signOut: 'Sign Out'
        },
        footer: {
            p1: {
                title: 'BRAVESMART',
                text: 'Made as an assigment for the college. I\'m not even Scottish.'
            },
            p2: {
                title: 'ABOUT US',
                text: 'BraveSmart is an entrypoint to Scottish culture and heritage. Discover museums and galleries ' +
                    'from your Corona-safe living room. To be brave, first you need to be smart. Otherwise, you\'re ' +
                    'just a full rushing to their end. Also, it\'s a pun.'
            },
            p3: {
                title: 'CONTACT',
                li1: 'Find the author on ',
                li1Link: 'GitHub',
                li2: 'Contact the ',
                li2Link: 'Faculty',
                li3: 'I\'m not giving you my phone number',
                li4: 'Send us a ',
                li4Link: 'Message'
            }
        },
        museumPanel: {
            filterResults: 'Filter results',
            country: 'Country',
            city: 'City',
            type: 'Type',
            all: 'All'
        },
        museumLoader: {
            failedToLoad: 'Failed to load. It\'s not you - it\'s us.',
            noResults: 'Nothing found. Try searching for something else.'
        },
        museumCard: {
            seeMore: 'SEE MORE',
            newTour: 'NEW TOUR'
        },
        layout: {
            noAccess: {
                title: 'No Access',
                text: 'You need to sign in to see this page.'
            },
            notAllowed: {
                title: 'No Access',
                text: 'You are not authorized to see this page.'
            }
        },
        page404: {
            title: 'Dead end',
            text: 'The page you\'re looking for was not found.'
        },
        index: {
            p1: 'This list of museums in Scotland contains museums which are defined for this context as ' +
                'institutions (including nonprofit organisations, government entities, and private businesses) that ' +
                'collect and care for objects of cultural, artistic, scientific, or historical interest and make ' +
                'their collections or related exhibits available for public viewing. Also included are non-profit ' +
                'art galleries and university art galleries. Museums that exist only in cyberspace (i.e., virtual ' +
                'museums) are not included. Many other small historical displays are located in the country\'s ' +
                'stately homes, castles and public libraries.',
            p2: 'This list of museums in Scotland contains museums which are defined for this context as ' +
                'institutions (including nonprofit organisations, government entities, and private businesses) that ' +
                'collect and care for objects of cultural, artistic, scientific, or historical interest and make ' +
                'their collections or related exhibits available for public viewing. Also included are non-profit ' +
                'art galleries and university art galleries. Museums that exist only in cyberspace (i.e., virtual ' +
                'museums) are not included. Many other small historical displays are located in the country\'s ' +
                'stately homes, castles and public libraries.',
        },
        museums: {
            search: 'Search',
            searchPlaceholder: 'Search for museums',
            endpointSlug: 'en'
        },
        signIn: {
            success: {
                title: 'Welcome',
                text: 'Welcome to BraveHeart. You can now browse museums and apply for virtual tours.'
            },
            progress: {
                title: 'Sign In',
                text: 'Signing you in.'
            }
        },
        signOut: {
            success: {
                title: 'Sign Out',
                text: 'You have successfully signed out.'
            },
            progress: {
                title: 'Sign Out',
                text: 'Hold on. We\'re signing you out.'
            }
        },
        newsPanel: {
            header: 'Culture and Art news from around the web'
        },
        errorAlert: {
            title: 'Error occurred',
            status: {
                '401': 'Make sure you are logged in.',
                '403': 'Make sure you are allowed to see the content.',
                '404': 'Make sure that the content you\'re looking for actually exists.',
                '500': 'Server error occurred. Please try again later.'
            }
        },
        tours: {
            pastMonth: 'Past Month',
            upcoming: 'Upcoming',
            bookedTours: 'Booked Tours',
            failedToLoad: 'No data. It\'s not you, it\'s us.',
            noUpcomingTours: 'No upcoming tours. Cultural famine, eh.',
            noRecentTours: 'No tours held recently.',
            noBookedTours: 'No booked tours. Check museums tab and book a few.',
            from: 'from',
            to: 'to',
            inProgress: 'IN PROGRESS',
            finished: 'FINISHED',
            buyTicket: 'BUY A TICKET',
            visit: 'Visit'
        },
        prettyDate: {
            dateFormat: 'L. d. yyyy'
        },
        purchase: {
            success: {
                title: 'Success',
                text: 'You\'ve successfully booked a tour. We\'ve sent you an email containing the digital ticket.'
            },
            error: {
                title: 'An Error Occurred',
                text: 'We couldn\'t process the payment. Bookmark this site and check it later. If the problem ' +
                    'persists, contact us.'
            }
        },
        messages: {
            text: 'Send us a message. If needed, we will contact you via email.',
            label: 'Message',
            submit: 'Send',
            successTitle: 'Message sent',
            successBody: 'Thanks! We always value our user\'s opinions - even when they\'re objectively wrong.',
            errorTitle: 'Message not sent',
            errorBody: 'Something broke. Please try again later.',
            back: 'Back',
            delete: 'Delete',
            noMessagesTitle: 'No Messages',
            noMessagesBody: 'No user messages at the moment.',
        },
        users: {
            totalUsers: 'Total users: ',
            username: 'Username',
            firstName: 'First Name',
            lastName: 'Last Name',
            email: 'Email Address',
            resetPassword: 'Reset Password',
            resetPasswordButton: 'Reset',
            disableLogin: 'Disable Login',
            disableLoginButton: 'Disable'
        },
        stats: {
            activeUsers: 'Active Users',
            atTheMoment: 'right now',
            downloadLogs: 'Download Logs',
            userId: 'User ID',
            time: 'Time',
            category: 'Category',
            subcategory: 'Subcategory',
            label: 'Label',
            value: 'Value',
            failedToLoad: 'Failed to load'
        },
        addMuseum: {
            name: 'Name',
            address: 'Address',
            city: 'City',
            country: 'Country',
            phoneNumber: 'Phone Number',
            museumType: 'Museum Type',
            googleLocation: 'Google Embedded Map URL',
            submit: 'Add Museum',
            language: 'Language',
            back: 'Back',
            successTitle: 'Museum added',
            successBody: 'You\'ve successfully added a new museum entry set. Check the museum list in a few minutes.',
            errorTitle: 'There was an error',
            errorBody: 'Double-check the data and try again.'
        },
        addTour: {
            startTime: "Start",
            duration: "Duration (hours)",
            title: "Title",
            description: "Description",
            pictures: "Pictures (5-10 pictures)",
            ticketPrice: "Ticket Price",
            video: "Video",
            videoLink: "Link",
            videoFile: "Video File",
            submit: "Create Tour",
            error: {
                badPictureCount: "Please provide between 5 and 10 images.",
                badDates: "Please provide start time and duration.",
                noTitle: "Please write a title.",
                noDescription: "Please write a description.",
                noVideoFile: "Please provide a video file.",
                noVideoLink: "Please provide a link to video.",
                noTicketPrice: "Please provide the ticket price."
            }
        },
        visit: {
            "enter": "Enter",
            "textAreaPlaceholder": "Enter your ticket id",
            "error": "Get fukked"
        }
    },
    sr: {
        museumProfile: {
            category: 'Kategorija',
            contact: 'Kontakt',
            tours: 'Nadolazece Posjete',
            noTours: 'Nema nadolazećih posjeta.'
        },
        weatherPanel: {
            endpointSlug: 'hr',
            feelsLike: ', subjektivni osjećaj '
        },
        header: {
            museums: 'Muzeji',
            tours: 'Obilasci',
            visit: 'Posjeti',
            welcome: 'Dobrodošli',
            messages: 'Poruke',
            addMuseums: 'Dodaj Muzej',
            stats: 'Statistika',
            users: 'Korisnici',
            signIn: 'Prijava',
            signOut: 'Odjava'
        },
        footer: {
            p1: {
                title: 'BRAVESMART',
                text: 'Urađeno u sklopu projektnog zadatka. Ja ni nisam iz Škotske.'
            },
            p2: {
                title: 'O NAMA',
                text: 'BraveSmart je vaša ulazna tačka u škotsku kulturu i naslijeđe. Otkrijte muzeje i galerije iz' +
                    'vaše dnevne sobe. Da bi bili hrabri, prvo morate biti pametni - u suprotnom ste obični uhljub; ' +
                    'jungija; tetres; tarzan.'
            },
            p3: {
                title: 'KONTAKT',
                li1: 'Kontaktirajte autora kroz ',
                li1Link: 'Githab',
                li2: 'Kontaktirajte ',
                li2Link: 'fakultet',
                li3: 'Ne dam broj telefona',
                li4: 'Pošaljite nam ',
                li4Link: 'poruku'
            }
        },
        museumPanel: {
            filterResults: 'Filtriraj rezultate',
            country: 'Država',
            city: 'Grad',
            type: 'Tip',
            all: 'Sve'
        },
        museumLoader: {
            failedToLoad: 'Pretraga nije uspjela. Do nas je.',
            noResults: 'Nema rezultata. Probajte tražiti nešto opštije.'
        },
        museumCard: {
            seeMore: 'DETALJNIJE',
            newTour: 'NOVA POSJETA'
        },
        layout: {
            noAccess: {
                title: 'Zabranjen pristup',
                text: 'Morate biti prijavljeni da bi vidjeli ovu stranicu.'
            },
            notAllowed: {
                title: 'Zabranjen pristup',
                text: 'Nemate privilegije da vidite ovu stranicu.'
            }
        },
        page404: {
            title: 'Nema sadržaja',
            text: 'Stranica koju tražite ne postoji.'
        },
        index: {
            p1: 'This list of museums in Scotland contains museums which are defined for this context as ' +
                'institutions (including nonprofit organisations, government entities, and private businesses) that ' +
                'collect and care for objects of cultural, artistic, scientific, or historical interest and make ' +
                'their collections or related exhibits available for public viewing. Also included are non-profit ' +
                'art galleries and university art galleries. Museums that exist only in cyberspace (i.e., virtual ' +
                'museums) are not included. Many other small historical displays are located in the country\'s ' +
                'stately homes, castles and public libraries.',
            p2: 'This list of museums in Scotland contains museums which are defined for this context as ' +
                'institutions (including nonprofit organisations, government entities, and private businesses) that ' +
                'collect and care for objects of cultural, artistic, scientific, or historical interest and make ' +
                'their collections or related exhibits available for public viewing. Also included are non-profit ' +
                'art galleries and university art galleries. Museums that exist only in cyberspace (i.e., virtual ' +
                'museums) are not included. Many other small historical displays are located in the country\'s ' +
                'stately homes, castles and public libraries.',
        },
        museums: {
            search: 'Pretraži',
            searchPlaceholder: 'Pretraži muzeje po imenu ili drugim parametrima',
            endpointSlug: 'sr'
        },
        signIn: {
            success: {
                title: 'Dobrodošli',
                text: 'Dobrodošli na BraveHeart. Sad možete pregledati muzeje i kupovati virtuelne karte za posjete.'
            },
            progress: {
                title: 'Prijava',
                text: 'Molimo, sačekajte.'
            }
        },
        signOut: {
            success: {
                title: 'Odjava',
                text: 'Uspješno ste se odjavili.'
            },
            progress: {
                title: 'Odjava',
                text: 'Molimo, sačekajte.'
            }
        },
        newsPanel: {
            header: 'Vijesti iz kulture'
        },
        errorAlert: {
            title: 'Dogodila se greška',
            status: {
                '401': 'Provjerite da li ste prijavljeni.',
                '403': 'Budite sigurni da imate pravo da vidite ovaj sadržaj.',
                '404': 'Provjerite da li tražite postojeći sadržaj.',
                '500': 'Dogodila se greška na serveru. Molimo, probajte kasnije.'
            }
        },
        tours: {
            pastMonth: 'Prošli mjesec',
            upcoming: 'Uskoro',
            bookedTours: 'Uplaćeno',
            failedToLoad: 'Nema podataka. Do nas je.',
            noUpcomingTours: 'Nijedan obilazak se ne održava uskoro.',
            noRecentTours: 'Nijedan obilazak se nije održao u skorije vrijeme.',
            noBookedTours: 'Niste se prijavili ni za jedan obilazak.',
            from: 'od',
            to: 'do',
            inProgress: 'U TOKU',
            finished: 'ZAVRŠENO',
            buyTicket: 'KUPI KARTU',
            visit: 'Posjeti'
        },
        prettyDate: {
            dateFormat: 'd. L. yyyy'
        },
        purchase: {
            success: {
                title: 'Čestitamo',
                text: 'Uspješno ste kupili kartu za virtuelnu posjetu. Digitalnu kartu možete pronaći u svom mejl ' +
                    'sandučetu.'
            },
            error: {
                title: 'Greška',
                text: 'Ne možemo da potvrdimo vašu uplatu. Sačuvajte ovu stranicu, te je posjetite malo kasnije. U ' +
                    'slučaju da ponovo dobijete ovu grešku, kontaktirajte nas.'
            }
        },
        messages: {
            text: 'Pošaljite nam poruku. Ako situacija to zahtjeva, kontaktiraćemo vas putem imejla.',
            label: 'Poruka',
            submit: 'Pošalji',
            successTitle: 'Poruka poslata',
            successBody: 'Hvala! Poruka je uspješno poslata.',
            errorTitle: 'Poruka nije poslata',
            errorBody: 'Došlo je do greške. Molimo, probajte kasnije.',
            back: 'Nazad',
            delete: 'Obriši',
            noMessagesTitle: 'Nema poruka',
            noMessagesBody: 'Trenutno nema nijedne korisničke poruke.',
        },
        users: {
            totalUsers: 'Ukupno korisnika',
            username: 'Korisničko ime',
            firstName: 'Ime',
            lastName: 'Prezime',
            email: 'Imejl adresa',
            resetPassword: 'Resetuj šifru',
            resetPasswordButton: 'Resetuj',
            disableLogin: 'Onemogući prijavu',
            disableLoginButton: 'Onemogući'
        },
        stats: {
            activeUsers: 'Aktivni korisnici',
            atTheMoment: 'trenutno',
            downloadLogs: 'Preuzmi logove',
            userId: 'Korisnički ID',
            time: 'Vrijeme',
            category: 'Kategorija',
            subcategory: 'Podkategorija',
            label: 'Akcija',
            value: 'Vrijednost',
            failedToLoad: 'Neuspjelo učitavanje'
        },
        addMuseum: {
            name: 'Naziv',
            address: 'Adresa',
            city: 'Grad',
            country: 'Država',
            phoneNumber: 'Broj telefona',
            museumType: 'Tip muzeja',
            googleLocation: 'URL Gugl mape',
            submit: 'Dodaj muzej',
            language: 'Jezik',
            back: 'Nazad',
            successTitle: 'Uspjeh',
            successBody: 'Uspješno ste dodali novi muzej. Dostupna lista muzeja ce se azurirati kroz koji minut.',
            errorTitle: 'Greška',
            errorBody: 'Molimo, provjerite unešene podatke i probajte ponovo.'
        },
        addTour: {
            startTime: "Početak",
            duration: "Trajanje (sati)",
            title: "Naziv",
            description: "Opis",
            pictures: "Slike (5-10 slika)",
            ticketPrice: "Cijena ulaznice",
            video: "Video",
            videoLink: "Link",
            videoFile: "Video fajl",
            submit: "Napravi posjetu",
            error: {
                badPictureCount: "Izmedju 5 i 10 slika, molicu lijepo.",
                badDates: "Unesite validan početak i trajanje posjete.",
                noTitle: "Unesite naziv posjete.",
                noDescription: "Unesite kratki opis.",
                noVideoFile: "Obezbjedite video fajl.",
                noVideoLink: "Unesite link do videa.",
                noTicketPrice: "Unesite cijenu ulaznice."
            }
        },
        visit: {
            "enter": "Posjeti",
            "textAreaPlaceholder": "Unesite kod ulaznice",
            "error": "Jbgdruye"
        }
    }
}

export default fullL10n