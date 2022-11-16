import Layout from "../../components/layout";
import TourVisit from "../../components/tourVisit";

export default function Visit(props) {
    return (
        <Layout authenticated>
            <TourVisit tourId={props.id}/>
        </Layout>
    )
}

export async function getStaticProps({params}) {
    return {
        props: {
            id: params.id
        }
    }
}

export async function getStaticPaths() {
    return {
        paths: [
            {
                params: {
                    id: '1'
                }
            }
        ],
        fallback: true
    }
}