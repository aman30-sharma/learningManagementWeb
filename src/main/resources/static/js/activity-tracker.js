// Activity Tracking
let activityStartTime = Date.now();

// Track page view
window.addEventListener('load', () => {
    trackActivity('PAGE_VIEW', window.location.pathname);
});

// Track time spent
window.addEventListener('beforeunload', () => {
    const timeSpent = Math.floor((Date.now() - activityStartTime) / 1000);
    trackActivity('TIME_SPENT', `${timeSpent} seconds`);
});

// Track course access
function trackCourseAccess(courseId) {
    trackActivity('COURSE_ACCESS', `Course ID: ${courseId}`);
}

// Track module view
function trackModuleView(moduleId) {
    trackActivity('MODULE_VIEW', `Module ID: ${moduleId}`);
}

// Send activity to server
function trackActivity(type, details) {
    fetch('/api/activity/track', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            activityType: type,
            details: details,
            timestamp: new Date().toISOString()
        })
    }).catch(err => console.log('Activity tracking failed:', err));
}
