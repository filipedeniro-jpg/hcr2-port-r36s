//
// You can use these functions to customize which items and properties
// are exported to the raw info file. Each function gives you an item,
// and the function should return true if that item is to be exported.
// The shouldExportProperty functions also give you a property name to
// use to decide whether that property should be exported for the item.
//
//     shouldExportItem : return true if you want to export the item
//     shouldExportProperty : return true to export the property for the item
//
// Uncomment the functions below as necessary to customize your exporting.
// Do not change the callsign of the functions, or they will be ignored.
//
// If functions are left commented out, the default is to export.
//


// ========== World exporting ==========
/*
bool shouldExportProperty( world w, const string& in propertyName ) {
    return true;
}
*/
// ========== Body exporting ==========
bool shouldExportItem( body b ) {
    if(b.getName() == "ignore") return false;
     return true; 
} 


/*bool shouldExportProperty( body b, const string& in propertyName ) {
    return true;
}*/

// ========== Fixture exporting ==========
/*
bool shouldExportItem( fixture f ) {
    return true;
}

bool shouldExportProperty( fixture f, const string& in propertyName ) {
    return true;
}
*/
// ========== Joint exporting ==========
/*
bool shouldExportItem( joint j ) {
    return true;
}

bool shouldExportProperty( joint j, const string& in propertyName ) {
    return true;
}
*/
// ========== Image exporting ==========
/*
bool shouldExportItem( image i ) {
    return true;
}

bool shouldExportProperty( image i, const string& in propertyName ) {
    return true;
}
*/
// ========== Object exporting ==========
/*
bool shouldExportItem( object i ) {
    return true;
}

bool shouldExportProperty( object i, const string& in propertyName ) {
    return true;
}
*/


// ==========================
// This is to stop the auto-compile of the script panel
// from detecting errors that are not really errors.
void main(){}
