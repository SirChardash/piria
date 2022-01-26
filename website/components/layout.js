import Head from 'next/head'
import styles from '../styles/layout.module.css'
import Header from "./header";
import Footer from "./footer";
import Box from "@mui/material/Box";
import {Container} from "@mui/material";

export default function Layout({children}) {
    return (
        <div className={styles.container}>

            <Header/>
            <Box className={styles.container}>
                <Head>
                    <title>BraveSmart</title>
                </Head>
                <main>
                    <Container style={{marginTop: '45px'}}>
                        {children}
                    </Container>
                </main>
                <Footer/>
            </Box>

        </div>
    )
}
