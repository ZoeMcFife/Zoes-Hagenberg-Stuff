public class Main
{
    public static void main(String[] args)
    {
        // a bunch of if stamtement about player health and max player health
        int playerHealth = 75;
        int maxPlayerHealth = 100;

        if (playerHealth < maxPlayerHealth && playerHealth >= 50)
        {
            System.out.println("Player is healthy.");
        }
        else if (playerHealth < 50 && playerHealth >= 20)
        {
            System.out.println("Player is injured.");
        }
        else if (playerHealth < 20 && playerHealth > 0)
        {
            System.out.println("Player is critically injured.");
        }
        else if (playerHealth <= 0)
        {
            System.out.println("Player is dead.");
        }
        else
        {
            System.out.println("Player is at full health.");
        }
    }
}