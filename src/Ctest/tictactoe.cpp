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
    string array[3][3] = {{"0", "0", "0"}, {"0", "0", "0"}, {"0", "0", "0"}};
    while (true)
    {

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cout << array[i][j];
            }
            cout << endl;
        }
        int a;
        cout << "which position?" << endl;
        cin >> a;
        switch (a)
        {
        case a == 1:
            cout << "_|";
            break;

        default:
            NULL;
            break;
        }
    }