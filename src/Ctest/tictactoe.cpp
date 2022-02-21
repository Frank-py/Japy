#include <iostream>
#include <string>
using namespace std;
void game();
int main(int argc, char const *argv[])
{
    string s;
    cout << "Wie heißt Spieler1?\n";
    cin >> s;
    cout << "du heißt " << s << " und bist x" << endl;

    string v;
    cout << "Wie heißt Spieler2?\n";
    cin >> v;
    cout << "du heißt " << v << " und bist o" << endl;

    game();
    return 0;
}

void game()
{
    
}