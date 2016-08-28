import React from 'react';
import 'whatwg-fetch';
import {addBuildSummary} from './Actions.es6'

const receiveBuildSummaries = (dispatch) => {
  const endpoint = "http://localhost:4444/summaries";

  let dummy = [

     { buildId: 1, state: "success", buildNumber: 1, startTime: "1:12", duration: "2 minutes" },
     { buildId: 2, buildNumber: 2, state: "failed" },
     { buildId: 4, buildNumber: 4, state: "running"}

  ]

  fetch(endpoint).then(() => {})
    .then(body=>dispatch(addBuildSummary(body.summaries)))
    .catch(()=>dispatch(addBuildSummary(dummy)));



}

export { receiveBuildSummaries };
