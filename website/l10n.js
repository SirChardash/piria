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
            buyTicket: 'BUY A TICKET'
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
        }
    },
    sr: {
        museumProfile: {
            category: 'Kategorija',
            contact: 'Kontakt',
            tours: 'Nadolazece Posjete',
            noTours: 'Nema nadolaze??ih posjeta.'
        },
        weatherPanel: {
            endpointSlug: 'hr',
            feelsLike: ', subjektivni osje??aj '
        },
        header: {
            museums: 'Muzeji',
            tours: 'Obilasci',
            visit: 'Posjeti',
            welcome: 'Dobrodo??li',
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
                text: 'Ura??eno u sklopu projektnog zadatka. Ja ni nisam iz ??kotske.'
            },
            p2: {
                title: 'O NAMA',
                text: 'BraveSmart je va??a ulazna ta??ka u ??kotsku kulturu i naslije??e. Otkrijte muzeje i galerije iz' +
                    'va??e dnevne sobe. Da bi bili hrabri, prvo morate biti pametni - u suprotnom ste obi??ni uhljub; ' +
                    'jungija; tetres; tarzan.'
            },
            p3: {
                title: 'KONTAKT',
                li1: 'Kontaktirajte autora kroz ',
                li1Link: 'Githab',
                li2: 'Kontaktirajte ',
                li2Link: 'fakultet',
                li3: 'Ne dam broj telefona',
                li4: 'Po??aljite nam ',
                li4Link: 'poruku'
            }
        },
        museumPanel: {
            filterResults: 'Filtriraj rezultate',
            country: 'Dr??ava',
            city: 'Grad',
            type: 'Tip',
            all: 'Sve'
        },
        museumLoader: {
            failedToLoad: 'Pretraga nije uspjela. Do nas je.',
            noResults: 'Nema rezultata. Probajte tra??iti ne??to op??tije.'
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
            title: 'Nema sadr??aja',
            text: 'Stranica koju tra??ite ne postoji.'
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
            search: 'Pretra??i',
            searchPlaceholder: 'Pretra??i muzeje po imenu ili drugim parametrima',
            endpointSlug: 'sr'
        },
        signIn: {
            success: {
                title: 'Dobrodo??li',
                text: 'Dobrodo??li na BraveHeart. Sad mo??ete pregledati muzeje i kupovati virtuelne karte za posjete.'
            },
            progress: {
                title: 'Prijava',
                text: 'Molimo, sa??ekajte.'
            }
        },
        signOut: {
            success: {
                title: 'Odjava',
                text: 'Uspje??no ste se odjavili.'
            },
            progress: {
                title: 'Odjava',
                text: 'Molimo, sa??ekajte.'
            }
        },
        newsPanel: {
            header: 'Vijesti iz kulture'
        },
        errorAlert: {
            title: 'Dogodila se gre??ka',
            status: {
                '401': 'Provjerite da li ste prijavljeni.',
                '403': 'Budite sigurni da imate pravo da vidite ovaj sadr??aj.',
                '404': 'Provjerite da li tra??ite postoje??i sadr??aj.',
                '500': 'Dogodila se gre??ka na serveru. Molimo, probajte kasnije.'
            }
        },
        tours: {
            pastMonth: 'Pro??li mjesec',
            upcoming: 'Uskoro',
            bookedTours: 'Upla??eno',
            failedToLoad: 'Nema podataka. Do nas je.',
            noUpcomingTours: 'Nijedan obilazak se ne odr??ava uskoro.',
            noRecentTours: 'Nijedan obilazak se nije odr??ao u skorije vrijeme.',
            noBookedTours: 'Niste se prijavili ni za jedan obilazak.',
            from: 'od',
            to: 'do',
            inProgress: 'U TOKU',
            finished: 'ZAVR??ENO',
            buyTicket: 'KUPI KARTU'
        },
        prettyDate: {
            dateFormat: 'd. L. yyyy'
        },
        purchase: {
            success: {
                title: '??estitamo',
                text: 'Uspje??no ste kupili kartu za virtuelnu posjetu. Digitalnu kartu mo??ete prona??i u svom mejl ' +
                    'sandu??etu.'
            },
            error: {
                title: 'Gre??ka',
                text: 'Ne mo??emo da potvrdimo va??u uplatu. Sa??uvajte ovu stranicu, te je posjetite malo kasnije. U ' +
                    'slu??aju da ponovo dobijete ovu gre??ku, kontaktirajte nas.'
            }
        },
        messages: {
            text: 'Po??aljite nam poruku. Ako situacija to zahtjeva, kontaktira??emo vas putem imejla.',
            label: 'Poruka',
            submit: 'Po??alji',
            successTitle: 'Poruka poslata',
            successBody: 'Hvala! Poruka je uspje??no poslata.',
            errorTitle: 'Poruka nije poslata',
            errorBody: 'Do??lo je do gre??ke. Molimo, probajte kasnije.',
            back: 'Nazad',
            delete: 'Obri??i',
            noMessagesTitle: 'Nema poruka',
            noMessagesBody: 'Trenutno nema nijedne korisni??ke poruke.',
        },
        users: {
            totalUsers: 'Ukupno korisnika',
            username: 'Korisni??ko ime',
            firstName: 'Ime',
            lastName: 'Prezime',
            email: 'Imejl adresa',
            resetPassword: 'Resetuj ??ifru',
            resetPasswordButton: 'Resetuj',
            disableLogin: 'Onemogu??i prijavu',
            disableLoginButton: 'Onemogu??i'
        },
        stats: {
            activeUsers: 'Aktivni korisnici',
            atTheMoment: 'trenutno',
            downloadLogs: 'Preuzmi logove',
            userId: 'Korisni??ki ID',
            time: 'Vrijeme',
            category: 'Kategorija',
            subcategory: 'Podkategorija',
            label: 'Akcija',
            value: 'Vrijednost',
            failedToLoad: 'Neuspjelo u??itavanje'
        },
        addMuseum: {
            name: 'Naziv',
            address: 'Adresa',
            city: 'Grad',
            country: 'Dr??ava',
            phoneNumber: 'Broj telefona',
            museumType: 'Tip muzeja',
            googleLocation: 'URL Gugl mape',
            submit: 'Dodaj muzej',
            language: 'Jezik',
            back: 'Nazad',
            successTitle: 'Uspjeh',
            successBody: 'Uspje??no ste dodali novi muzej. Dostupna lista muzeja ce se azurirati kroz koji minut.',
            errorTitle: 'Gre??ka',
            errorBody: 'Molimo, provjerite une??ene podatke i probajte ponovo.'
        }
    }
}

export default fullL10n