import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import styles from '../styles/header.module.css';
import Link from 'next/link';
import {useKeycloak} from "@react-keycloak/ssr";

export default function Header() {
    const {keycloak, initialized} = useKeycloak()

    const userButtons = initialized
        ? (keycloak?.authenticated
            ? <Button className={styles.button} onClick={keycloak.logout()}>Sign out</Button>
            : <Button className={styles.button} href={''}>Sign in</Button>)
        : <div/>

    return (
        <Box sx={{flexGrow: 1}}>
            <AppBar position="static" className={styles.header}>
                <Toolbar>
                    <Link href={'/'}>
                        <IconButton size="large" edge="start" color="inherit" aria-label="menu" sx={{mr: 2}}>
                            ⚔️
                        </IconButton>
                    </Link>
                    <Link href={'/museums'} className={styles.button}><Button className={styles.button}>Museums</Button></Link>
                    <Link href={'/tours'} className={styles.button}><Button
                        className={styles.button}>Tours</Button></Link>
                    <Link href={'/visit'} className={styles.button}><Button
                        className={styles.button}>Visit</Button></Link>
                    <Typography variant="h6" component="div" sx={{flexGrow: 1}}> </Typography>
                    {userButtons}
                </Toolbar>
            </AppBar>
        </Box>
    );
}