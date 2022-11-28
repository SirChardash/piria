import Layout from "../../../components/layout";
import NewTourCard from "../../../components/newTourCard";
import {useState} from "react";

export default function NewTour(params) {
    const [tour, setTour] = useState({})

    return (
        <Layout admin>
            <NewTourCard state={tour} onChange={setTour} museumId={params.museumId}/>
        </Layout>
    )
}

export async function getStaticProps({params}) {
    return {
        props: {
            museumId: params.museumId
        }
    }
}

export async function getStaticPaths() {
    return {
        paths: [
            {
                params: {
                    museumId: '1'
                }
            }
        ],
        fallback: true
    }
}