package sexy.criss.simple.prison.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sexy.criss.simple.prison.utils.ACommand;
import sexy.criss.simple.prison.utils.MenuUtils;

public class MineCommand extends ACommand {

    public MineCommand() {
        super("mine", "mines", "shaft");
        this.unavailableFromConsole();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        MenuUtils.showMinesMenu(p);
        return false;
    }
}
